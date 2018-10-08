package to.lova.spring.blaze.collection.model;

import java.util.Set;

import com.blazebit.persistence.view.CreatableEntityView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.UpdatableEntityView;
import com.blazebit.persistence.view.UpdatableMapping;

@EntityView(Group.class)
@CreatableEntityView
@UpdatableEntityView
public interface GroupDetail {

    @IdMapping
    Long getId();

    @UpdatableMapping
    Set<UserDetail> getUsers();

}
