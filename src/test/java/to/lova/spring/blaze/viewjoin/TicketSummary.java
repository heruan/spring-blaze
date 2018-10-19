package to.lova.spring.blaze.viewjoin;

import java.time.Instant;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.blazebit.persistence.CTE;
import com.blazebit.persistence.SubqueryInitiator;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.FetchStrategy;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.Mapping;
import com.blazebit.persistence.view.MappingCorrelatedSimple;
import com.blazebit.persistence.view.MappingSubquery;
import com.blazebit.persistence.view.SubqueryProvider;

import to.lova.spring.blaze.collection.model.UserDetail;
import to.lova.spring.blaze.misc.model.AbstractCustomerSummary;

@EntityView(Ticket.class)
public interface TicketSummary {

    @IdMapping
    Long getNumber();

    Instant getCreationInstant();

    @Mapping("max(comments.creationInstant)")
    Instant getLastCommentInstant();

    @Mapping("size(comments)")
    long getCommentCount();

    @Mapping(value = "comments.author", fetch = FetchStrategy.SUBSELECT)
    Set<UserDetail> getCommentAuthors();

    String getSubject();

    AbstractCustomerSummary getCustomer();

    @MappingSubquery(value = SeenProvider.class, subqueryAlias = "seen",
            expression = "coalesce(seen, false)")
    boolean isSeen();

    class SeenProvider implements SubqueryProvider {

        @Override
        public <T> T createSubquery(SubqueryInitiator<T> subqueryInitiator) {
            return subqueryInitiator.from("embedding_view(seen)", "s")
                    .select("true").where("s.observer")
                    .eqExpression(":observer").setMaxResults(1).end();
        }

    }

    @MappingCorrelatedSimple(correlated = TicketCommentsCTE.class,
            correlationBasis = "this",
            correlationExpression = "ticketNumber = embedding_view(number)",
            fetch = FetchStrategy.JOIN)
    TicketCommentAggregate getCommentAggregates();

    default long getUnseenCommentsCount() {
        return this.getCommentCount()
                - this.getCommentAggregates().getSeenCommentCount();
    }

    default boolean isHasUnseenComments() {
        return this.getCommentAggregates().getSeenCommentCount() > 0;
    }

    @EntityView(TicketCommentsCTE.class)
    interface TicketCommentAggregate {

        Long getTicketNumber();

        @Mapping("coalesce(totalCommentCount, 0L)")
        long getTotalCommentCount();

        @Mapping("coalesce(seenCommentCount, 0L)")
        long getSeenCommentCount();

    }

    @CTE
    @Entity
    class TicketCommentsCTE {
        @Id
        Long ticketNumber;
        long totalCommentCount;
        long seenCommentCount;
    }

    @MappingSubquery(CteRegistrationSubqueryProvider.class)
    Integer getUnused();

    class CteRegistrationSubqueryProvider implements SubqueryProvider {

        @Override
        public <T> T createSubquery(SubqueryInitiator<T> subqueryInitiator) {
            // @formatter:off
            return subqueryInitiator
                .fromValues(Integer.class, "v", Collections.singletonList(1))
                .select("1")
                .with(TicketCommentsCTE.class)
                    .from(TicketComment.class, "c")
                    .bind("ticketNumber").select("c.ticket.number")
                    .bind("totalCommentCount").select("count(*)")
                    .bind("seenCommentCount").selectSubquery("seenComments", "sum(seenComments)")
                        .from("c.seen", "s").select("1")
                        .where("s.observer").eqExpression(":observer")
                        .end()
                    .end()
                .end();
                // @formatter:on
        }

    }

}
