package to.lova.spring.blaze.model.ticket.view;

import com.blazebit.persistence.view.CreatableEntityView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.UpdatableEntityView;

import to.lova.spring.blaze.model.common.view.UserBase;
import to.lova.spring.blaze.model.ticket.entity.TicketComment;

@EntityView(TicketComment.class)
@CreatableEntityView
@UpdatableEntityView
public interface TicketCommentDetailUpdatable extends TicketCommentDetail {

    void setAuthor(UserBase author);

    void setAssignee(UserBase assignee);

}
