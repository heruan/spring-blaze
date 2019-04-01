package to.lova.spring.blaze.model.common.view;

import java.util.Set;

import com.blazebit.persistence.view.CreatableEntityView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.UpdatableEntityView;
import com.blazebit.persistence.view.UpdatableMapping;

import to.lova.spring.blaze.model.common.entity.Group;

@EntityView(Group.class)
@CreatableEntityView
@UpdatableEntityView
public interface GroupDetail {

    @IdMapping
    Long getId();

    @UpdatableMapping
    Set<UserDetail> getUsers();

}
