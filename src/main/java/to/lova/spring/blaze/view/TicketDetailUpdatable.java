package to.lova.spring.blaze.view;

import com.blazebit.persistence.view.CreatableEntityView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.UpdatableEntityView;

import to.lova.spring.blaze.model.Ticket;

@EntityView(Ticket.class)
@CreatableEntityView
@UpdatableEntityView
public interface TicketDetailUpdatable extends TicketDetail {

    void setCustomer(AbstractCustomerBase customer);

    void setAuthor(UserBase author);

    void setAssignee(UserBase assignee);

    void setOpen(boolean open);

}
