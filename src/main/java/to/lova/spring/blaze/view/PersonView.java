package to.lova.spring.blaze.view;

import com.blazebit.persistence.view.CreatableEntityView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.UpdatableEntityView;

import to.lova.spring.blaze.entity.Person;

@EntityView(Person.class)
@CreatableEntityView
@UpdatableEntityView
public interface PersonView {

    @IdMapping
    Long getId();

    String getName();

    void setName(String name);

}
