package to.lova.spring.blaze.model.common.view;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;

import to.lova.spring.blaze.model.common.entity.User;

@EntityView(User.class)
public interface UserBase {

    @IdMapping
    Long getId();

}
