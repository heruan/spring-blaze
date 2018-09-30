package to.lova.spring.blaze.entity;

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
