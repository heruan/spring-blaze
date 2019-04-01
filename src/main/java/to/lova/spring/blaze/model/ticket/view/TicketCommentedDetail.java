package to.lova.spring.blaze.model.ticket.view;

import com.blazebit.persistence.view.CreatableEntityView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.UpdatableEntityView;

import to.lova.spring.blaze.model.ticket.entity.TicketCommented;

@EntityView(TicketCommented.class)
@CreatableEntityView
@UpdatableEntityView
public interface TicketCommentedDetail extends TicketHistoryDetail {

    TicketCommentDetail getComment();

    void setComment(TicketCommentDetail comment);

}
