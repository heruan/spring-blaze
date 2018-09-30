package to.lova.spring.blaze.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("address")
public class ShippingAddress extends AbstractCustomer {

    @ManyToOne
    private Customer customer;

    public Customer getCustomer() {
        return this.customer;
    }

}
