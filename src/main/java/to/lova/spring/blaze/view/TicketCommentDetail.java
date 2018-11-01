package to.lova.spring.blaze.view;

import java.util.List;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;

import to.lova.spring.blaze.model.TicketComment;

@EntityView(TicketComment.class)
public interface TicketCommentDetail {

    @IdMapping
    Long getId();

    UserBase getAuthor();

    UserBase getAssignee();

    List<AttachmentView> getAttachments();

}
