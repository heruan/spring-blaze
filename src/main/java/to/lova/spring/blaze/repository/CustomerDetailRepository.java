package to.lova.spring.blaze.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import to.lova.spring.blaze.view.AbstractCustomerDetail;

public interface CustomerDetailRepository
        extends JpaRepository<AbstractCustomerDetail, Long> {

    Optional<AbstractCustomerDetail> findByName(String name);

}
