package to.lova.spring.blaze.model.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import to.lova.spring.blaze.model.customer.view.AbstractCustomerSummary;

public interface CustomerSummaryRepository
        extends JpaRepository<AbstractCustomerSummary, Long> {

}
