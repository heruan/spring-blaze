package to.lova.spring.blaze.view;

import java.util.Collections;
import java.util.Set;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.blazebit.persistence.CTE;
import com.blazebit.persistence.JoinType;
import com.blazebit.persistence.SubqueryInitiator;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.EntityViewInheritance;
import com.blazebit.persistence.view.FetchStrategy;
import com.blazebit.persistence.view.Mapping;
import com.blazebit.persistence.view.MappingCorrelatedSimple;
import com.blazebit.persistence.view.MappingSubquery;
import com.blazebit.persistence.view.SubqueryProvider;

import to.lova.spring.blaze.model.AbstractCustomer;
import to.lova.spring.blaze.model.ServiceContract;
import to.lova.spring.blaze.model.ShippingAddress;
import to.lova.spring.blaze.model.Ticket;

@EntityView(AbstractCustomer.class)
@EntityViewInheritance
public interface AbstractCustomerSummary extends AbstractCustomerBase {

    @MappingCorrelatedSimple(correlated = TicketCountCte.class,
            correlationBasis = "this",
            correlationExpression = "customerId = embedding_view(id)",
            fetch = FetchStrategy.JOIN)
    TicketAggregateView getTicketAggregates();

    @EntityView(TicketCountCte.class)
    interface TicketAggregateView {

        String getCustomerId();

        @Mapping("coalesce(totalTicketCount, 0L)")
        long getTotalTicketCount();

        @Mapping("coalesce(openTicketCount, 0L)")
        long getOpenTicketCount();

    }

    @CTE
    @Entity
    class TicketCountCte {
        @Id
        String customerId;
        long totalTicketCount;
        long openTicketCount;
    }

    @MappingCorrelatedSimple(correlated = ContractCountCte.class,
            correlationBasis = "this",
            correlationExpression = "customerId = embedding_view(id)",
            fetch = FetchStrategy.JOIN)
    ContractAggregateView getContractAggregates();

    @EntityView(ContractCountCte.class)
    interface ContractAggregateView {

        String getCustomerId();

        @Mapping("coalesce(totalContractCount, 0L)")
        long getTotalContractCount();

        @Mapping("coalesce(activeContractCount, 0L)")
        long getActiveContractCount();

    }

    @CTE
    @Entity
    class ContractCountCte {
        @Id
        String customerId;
        long totalContractCount;
        long activeContractCount;
        @ManyToMany
        @JoinTable(name = "service_contract_abstract_customer",
                joinColumns = @JoinColumn(name = "service_contract_id"),
                inverseJoinColumns = @JoinColumn(name = "addresses_id"),
                foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
        Set<ShippingAddress> addresses;
    }

    @MappingSubquery(CteRegistrationSubqueryProvider.class)
    Integer getUnused();

    class CteRegistrationSubqueryProvider implements SubqueryProvider {
        @Override
        public <T> T createSubquery(SubqueryInitiator<T> subqueryInitiator) {
            // @formatter:off
            return subqueryInitiator.fromValues(Integer.class, "t", Collections.singletonList(1)).select("1")
                .with(TicketCountCte.class)
                    .from(Ticket.class, "t")
                    .bind("customerId").select("t.customer.id")
                    .bind("totalTicketCount").select("count(*)")
                    .bind("openTicketCount").select("count(CASE WHEN t.active = true THEN 1 END)")
                .end()
                .with(ContractCountCte.class)
                    .from(ServiceContract.class, "c1")
                    .bind("customerId").select("c1.customer.id")
                    .bind("totalContractCount").select("count(*)")
                    .bind("activeContractCount").select("count(CASE WHEN c1.endingDate >= current_date THEN 1 END)")
                    .unionAll()
                    .from(ServiceContract.class, "c2")
                    .join("c2.addresses", "a", JoinType.INNER)
                    .bind("customerId").select("a.id")
                    .bind("totalContractCount").select("count(*)")
                    .bind("activeContractCount").select("count(CASE WHEN c2.endingDate >= current_date THEN 1 END)")
                    .endSet()
                .end()
            .end();
            // @formatter:on
        }
    }

}
