package to.lova.spring.blaze.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class ServiceContract {

    @Id
    private String id;

    @ManyToOne
    private AbstractCustomer customer;

    @ManyToMany
    @JoinTable(name = "service_contract_abstract_customer",
            joinColumns = @JoinColumn(name = "service_contract_id"),
            inverseJoinColumns = @JoinColumn(name = "addresses_id"),
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private List<ShippingAddress> addresses = new ArrayList<>();

    private LocalDate endingDate;

    public AbstractCustomer getCustomer() {
        return this.customer;
    }

    public void setCustomer(AbstractCustomer customer) {
        this.customer = customer;
    }

    public LocalDate getEndingDate() {
        return this.endingDate;
    }

    public void setEndingDate(LocalDate endingDate) {
        this.endingDate = endingDate;
    }

    public String getId() {
        return this.id;
    }

    public List<ShippingAddress> getAddresses() {
        return this.addresses;
    }

}
