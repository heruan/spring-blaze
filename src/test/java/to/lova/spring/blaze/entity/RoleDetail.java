package to.lova.spring.blaze.entity;

import java.util.Set;

import com.blazebit.persistence.view.CreatableEntityView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.InverseRemoveStrategy;
import com.blazebit.persistence.view.MappingInverse;
import com.blazebit.persistence.view.UpdatableEntityView;
import com.blazebit.persistence.view.UpdatableMapping;

@EntityView(Role.class)
@CreatableEntityView
@UpdatableEntityView
public interface RoleDetail {

    @IdMapping
    Long getId();

    @UpdatableMapping
    @MappingInverse(removeStrategy = InverseRemoveStrategy.REMOVE)
    Set<UserDetail> getUsers();

}
