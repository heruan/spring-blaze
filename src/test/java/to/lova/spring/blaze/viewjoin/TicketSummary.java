package to.lova.spring.blaze.viewjoin;

import java.time.Instant;
import java.util.Set;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.FetchStrategy;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.Mapping;

import to.lova.spring.blaze.collection.model.UserDetail;

@EntityView(Ticket.class)
public interface TicketSummary {

    @IdMapping
    Long getId();

    Instant getCreationInstant();

    @Mapping("max(comments.creationInstant)")
    Instant getLastCommentInstant();

    @Mapping("size(comments)")
    long getCommentCount();

    @Mapping(value = "comments.author", fetch = FetchStrategy.JOIN)
    Set<UserDetail> getCommentAuthors();

    String getSubject();

}
