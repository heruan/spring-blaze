package to.lova.spring.blaze.view;

import com.blazebit.persistence.view.CreatableEntityView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;

import to.lova.spring.blaze.model.Person;

@EntityView(Person.class)
@CreatableEntityView
public interface PersonView {

    @IdMapping
    Long getId();

    String getName();

    void setName(String name);

}
