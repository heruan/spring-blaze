package to.lova.spring.blaze.model.customer.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("customer")
public class Customer extends AbstractCustomer {

    boolean top;

    public boolean isTop() {
        return this.top;
    }

}
