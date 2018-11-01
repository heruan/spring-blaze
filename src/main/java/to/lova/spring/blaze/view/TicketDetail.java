package to.lova.spring.blaze.view;

import com.blazebit.persistence.view.EntityView;

import to.lova.spring.blaze.model.Ticket;

@EntityView(Ticket.class)
public interface TicketDetail extends TicketWithHistory {

    AbstractCustomerBase getCustomer();

    UserBase getAuthor();

    UserBase getAssignee();

    boolean isOpen();

}
