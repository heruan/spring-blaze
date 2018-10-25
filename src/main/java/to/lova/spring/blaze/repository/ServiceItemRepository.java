package to.lova.spring.blaze.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import to.lova.spring.blaze.model.ServiceItem;

public interface ServiceItemRepository
        extends JpaRepository<ServiceItem, String>,
        JpaSpecificationExecutor<ServiceItem> {

}
