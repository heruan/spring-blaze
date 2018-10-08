package to.lova.spring.blaze.misc.model;

import com.blazebit.persistence.view.EntityView;

import to.lova.spring.blaze.misc.model.Customer;

@EntityView(Customer.class)
public interface CustomerSummary extends AbstractCustomerSummary {

    boolean isTop();

}
