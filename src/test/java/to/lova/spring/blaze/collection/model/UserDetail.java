package to.lova.spring.blaze.collection.model;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;

@EntityView(User.class)
public interface UserDetail {

    @IdMapping
    Long getId();

}
