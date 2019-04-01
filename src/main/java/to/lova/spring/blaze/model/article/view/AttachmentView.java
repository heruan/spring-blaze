package to.lova.spring.blaze.model.article.view;

import com.blazebit.persistence.view.EntityView;

import to.lova.spring.blaze.model.article.entity.Attachment;

@EntityView(Attachment.class)
public interface AttachmentView {

    String getName();

}
