package to.lova.spring.blaze.misc.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDetailRepository
        extends JpaRepository<AbstractCustomerDetail, Long> {

}
