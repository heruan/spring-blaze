package to.lova.spring.blaze.view;

import java.util.List;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;

import to.lova.spring.blaze.model.Ticket;

@EntityView(Ticket.class)
public interface TicketDetail {

    @IdMapping
    Long getNumber();

    AbstractCustomerBase getCustomer();

    UserBase getAuthor();

    UserBase getAssignee();

    List<TicketHistoryDetail> getHistory();

    boolean isOpen();

}
