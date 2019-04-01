package to.lova.spring.blaze.model.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blazebit.persistence.spring.data.repository.EntityViewSpecificationExecutor;

import to.lova.spring.blaze.model.article.view.PersonView;
import to.lova.spring.blaze.model.common.entity.Person;

public interface PersonViewRepository extends JpaRepository<PersonView, Long>,
        EntityViewSpecificationExecutor<PersonView, Person> {

}
