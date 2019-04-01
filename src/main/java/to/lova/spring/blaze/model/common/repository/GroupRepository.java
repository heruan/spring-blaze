package to.lova.spring.blaze.model.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import to.lova.spring.blaze.model.common.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {

}
