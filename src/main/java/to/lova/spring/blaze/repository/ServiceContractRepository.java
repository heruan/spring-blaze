package to.lova.spring.blaze.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import to.lova.spring.blaze.model.ServiceContract;

public interface ServiceContractRepository
        extends JpaRepository<ServiceContract, String>,
        JpaSpecificationExecutor<ServiceContract> {

}
