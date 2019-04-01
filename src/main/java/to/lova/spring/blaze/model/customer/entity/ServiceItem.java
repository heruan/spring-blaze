package to.lova.spring.blaze.model.customer.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class ServiceItem implements Serializable {

    @Id
    private String id;

    private String serialNumber;

    private String description;

    @ManyToOne
    private AbstractCustomer customer;

    private String shippingDocument;

    @ManyToMany(mappedBy = ServiceContract_.SERVICE_ITEMS)
    private Set<ServiceContract> serviceContracts = new HashSet<>();

}
