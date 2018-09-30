package to.lova.spring.blaze.entity;

import com.blazebit.persistence.view.EntityView;

import to.lova.spring.blaze.entity.Customer;

@EntityView(Customer.class)
public interface CustomerSummary extends AbstractCustomerSummary {

    boolean isTop();

}
