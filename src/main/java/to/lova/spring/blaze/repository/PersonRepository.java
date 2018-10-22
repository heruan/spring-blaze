package to.lova.spring.blaze.repository;

import org.springframework.data.repository.CrudRepository;

import to.lova.spring.blaze.model.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

    long countByName(String name);

}
