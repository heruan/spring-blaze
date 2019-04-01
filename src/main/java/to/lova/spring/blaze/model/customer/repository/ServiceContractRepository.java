package to.lova.spring.blaze.model.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import to.lova.spring.blaze.model.customer.entity.ServiceContract;

public interface ServiceContractRepository
        extends JpaRepository<ServiceContract, String>,
        JpaSpecificationExecutor<ServiceContract> {

}
