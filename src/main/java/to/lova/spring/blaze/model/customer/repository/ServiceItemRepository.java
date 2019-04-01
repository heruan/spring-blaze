package to.lova.spring.blaze.model.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import to.lova.spring.blaze.model.customer.entity.ServiceItem;

public interface ServiceItemRepository
        extends JpaRepository<ServiceItem, String>,
        JpaSpecificationExecutor<ServiceItem> {

}
