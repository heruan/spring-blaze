package to.lova.spring.blaze.entity;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;

@EntityView(User.class)
public interface UserDetail {

    @IdMapping
    Long getId();

}
