package to.lova.spring.blaze.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blazebit.persistence.spring.data.repository.EntityViewSpecificationExecutor;

import to.lova.spring.blaze.model.Person;
import to.lova.spring.blaze.view.PersonView;

public interface PersonViewRepository extends JpaRepository<PersonView, Long>,
        EntityViewSpecificationExecutor<PersonView, Person> {

}
