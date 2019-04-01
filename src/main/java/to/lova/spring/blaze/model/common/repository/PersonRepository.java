package to.lova.spring.blaze.model.common.repository;

import org.springframework.data.repository.CrudRepository;

import to.lova.spring.blaze.model.common.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

    long countByName(String name);

}
