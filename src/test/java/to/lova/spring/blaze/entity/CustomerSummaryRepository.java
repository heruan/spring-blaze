package to.lova.spring.blaze.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerSummaryRepository
        extends JpaRepository<AbstractCustomerSummary, String> {

}
