package to.lova.spring.blaze.model.article.repository;

import org.springframework.data.repository.CrudRepository;

import to.lova.spring.blaze.model.article.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

    long countByName(String name);

}
