package to.lova.spring.blaze.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ServiceContractRepository
        extends JpaRepository<ServiceContract, String>,
        JpaSpecificationExecutor<ServiceContract> {

}
