package to.lova.spring.blaze.view;

import com.blazebit.persistence.view.EntityView;

import to.lova.spring.blaze.model.Customer;

@EntityView(Customer.class)
public interface CustomerSummary extends AbstractCustomerSummary {

}
