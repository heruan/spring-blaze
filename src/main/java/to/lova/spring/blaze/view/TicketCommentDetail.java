package to.lova.spring.blaze.view;

import com.blazebit.persistence.view.CreatableEntityView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.UpdatableEntityView;

import to.lova.spring.blaze.model.TicketComment;

@EntityView(TicketComment.class)
@CreatableEntityView
@UpdatableEntityView
public interface TicketCommentDetail {

    @IdMapping
    Long getId();

    UserDetail getAuthor();

    void setAuthor(UserDetail author);

    UserDetail getAssignee();

    void setAssignee(UserDetail assignee);

}
