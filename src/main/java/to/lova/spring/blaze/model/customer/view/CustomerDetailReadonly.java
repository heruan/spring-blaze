package to.lova.spring.blaze.model.customer.view;

import com.blazebit.persistence.view.EntityView;

import to.lova.spring.blaze.model.customer.entity.Customer;

@EntityView(Customer.class)
public interface CustomerDetailReadonly extends AbstractCustomerDetail {

    boolean isTop();

}
