package to.lova.spring.blaze.misc.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ServiceContractRepository
        extends JpaRepository<ServiceContract, String>,
        JpaSpecificationExecutor<ServiceContract> {

}
