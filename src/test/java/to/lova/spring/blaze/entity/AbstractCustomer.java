package to.lova.spring.blaze.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class AbstractCustomer extends Company {

    @Id
    private String id;

    @OneToMany(mappedBy = "customer")
    private List<ServiceContract> serviceContracts = new ArrayList<>();

    public String getId() {
        return this.id;
    }

    public List<ServiceContract> getServiceContracts() {
        return this.serviceContracts;
    }

}
