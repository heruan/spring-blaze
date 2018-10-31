package to.lova.spring.blaze.view;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;

import to.lova.spring.blaze.model.User;

@EntityView(User.class)
public interface UserBase {

    @IdMapping
    Long getId();

}
