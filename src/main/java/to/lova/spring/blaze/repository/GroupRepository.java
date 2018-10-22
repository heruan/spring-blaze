package to.lova.spring.blaze.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import to.lova.spring.blaze.model.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {

}
