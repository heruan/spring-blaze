package to.lova.spring.blaze.viewjoin;

import com.blazebit.persistence.view.CreatableEntityView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.UpdatableEntityView;

import to.lova.spring.blaze.collection.model.UserDetail;

@EntityView(TicketComment.class)
@CreatableEntityView
@UpdatableEntityView
public interface TicketCommentDetail {

    @IdMapping
    Long getId();

    TicketDetail getTicket();

    void setTicket(TicketDetail ticket);

    UserDetail getAuthor();

    void setAuthor(UserDetail author);

    UserDetail getAssignee();

    void setAssignee(UserDetail assignee);

}
