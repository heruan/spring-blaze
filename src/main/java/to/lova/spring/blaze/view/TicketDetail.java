package to.lova.spring.blaze.view;

import com.blazebit.persistence.view.CreatableEntityView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.UpdatableEntityView;

import to.lova.spring.blaze.model.Ticket;

@EntityView(Ticket.class)
@CreatableEntityView
@UpdatableEntityView
public interface TicketDetail {

    @IdMapping
    Long getNumber();

    AbstractCustomerDetail getCustomer();

    void setCustomer(AbstractCustomerDetail customer);

    UserDetail getAuthor();

    void setAuthor(UserDetail author);

    UserDetail getAssignee();

    void setAssignee(UserDetail assignee);

}
