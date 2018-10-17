package to.lova.spring.blaze.viewjoin;

import com.blazebit.persistence.view.CreatableEntityView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.UpdatableEntityView;

import to.lova.spring.blaze.collection.model.UserDetail;
import to.lova.spring.blaze.misc.model.AbstractCustomerDetail;

@EntityView(Ticket.class)
@CreatableEntityView
@UpdatableEntityView
public interface TicketDetail {

    @IdMapping
    Long getId();

    AbstractCustomerDetail getCustomer();

    void setCustomer(AbstractCustomerDetail customer);

    UserDetail getAuthor();

    void setAuthor(UserDetail author);

    UserDetail getAssignee();

    void setAssignee(UserDetail assignee);

}
