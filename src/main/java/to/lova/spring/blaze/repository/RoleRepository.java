package to.lova.spring.blaze.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import to.lova.spring.blaze.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
