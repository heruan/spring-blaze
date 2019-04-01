package to.lova.spring.blaze.model.customer.view;

import com.blazebit.persistence.view.CreatableEntityView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.UpdatableEntityView;

import to.lova.spring.blaze.model.customer.entity.Customer;

@EntityView(Customer.class)
@CreatableEntityView
@UpdatableEntityView
public interface CustomerDetail extends CustomerDetailReadonly {

    void setName(String name);

}
