package to.lova.spring.blaze.model.ticket.view;

import java.util.List;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;

import to.lova.spring.blaze.model.article.view.AttachmentView;
import to.lova.spring.blaze.model.common.view.UserBase;
import to.lova.spring.blaze.model.ticket.entity.TicketComment;

@EntityView(TicketComment.class)
public interface TicketCommentDetail {

    @IdMapping
    Long getId();

    UserBase getAuthor();

    UserBase getAssignee();

    List<AttachmentView> getAttachments();

}
