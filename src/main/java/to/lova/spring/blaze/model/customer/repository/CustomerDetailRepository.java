package to.lova.spring.blaze.model.customer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import to.lova.spring.blaze.model.customer.view.AbstractCustomerDetail;

public interface CustomerDetailRepository
        extends JpaRepository<AbstractCustomerDetail, Long> {

    Optional<AbstractCustomerDetail> findByName(String name);

}
