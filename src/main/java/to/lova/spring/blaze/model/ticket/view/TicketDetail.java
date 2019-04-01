package to.lova.spring.blaze.model.ticket.view;

import com.blazebit.persistence.view.EntityView;

import to.lova.spring.blaze.model.common.view.UserBase;
import to.lova.spring.blaze.model.customer.view.AbstractCustomerBase;
import to.lova.spring.blaze.model.ticket.entity.Ticket;

@EntityView(Ticket.class)
public interface TicketDetail extends TicketWithHistory {

    AbstractCustomerBase getCustomer();

    UserBase getAuthor();

    UserBase getAssignee();

    boolean isOpen();

}
