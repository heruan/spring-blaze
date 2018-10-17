package to.lova.spring.blaze.misc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class AbstractCustomer extends Company {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "customer")
    private List<ServiceContract> serviceContracts = new ArrayList<>();

    @Embedded
    private ServiceDetail serviceDetail = new ServiceDetail();

    public Long getId() {
        return this.id;
    }

    public List<ServiceContract> getServiceContracts() {
        return this.serviceContracts;
    }

    public ServiceDetail getServiceDetail() {
        return this.serviceDetail;
    }

}
