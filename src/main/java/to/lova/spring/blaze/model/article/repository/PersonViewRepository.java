package to.lova.spring.blaze.model.article.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blazebit.persistence.spring.data.repository.EntityViewSpecificationExecutor;

import to.lova.spring.blaze.model.article.entity.Person;
import to.lova.spring.blaze.model.article.view.PersonView;

public interface PersonViewRepository extends JpaRepository<PersonView, Long>,
        EntityViewSpecificationExecutor<PersonView, Person> {

}
