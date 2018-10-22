package to.lova.spring.blaze.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import to.lova.spring.blaze.view.AbstractCustomerSummary;

public interface CustomerSummaryRepository
        extends JpaRepository<AbstractCustomerSummary, Long> {

}
