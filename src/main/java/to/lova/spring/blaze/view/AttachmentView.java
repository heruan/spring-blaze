package to.lova.spring.blaze.view;

import com.blazebit.persistence.view.EntityView;

import to.lova.spring.blaze.model.Attachment;

@EntityView(Attachment.class)
public interface AttachmentView {

    String getName();

}
