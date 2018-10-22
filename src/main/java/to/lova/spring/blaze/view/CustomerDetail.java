package to.lova.spring.blaze.view;

import com.blazebit.persistence.view.CreatableEntityView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.UpdatableEntityView;

import to.lova.spring.blaze.model.Customer;

@EntityView(Customer.class)
@CreatableEntityView
@UpdatableEntityView
public interface CustomerDetail extends CustomerDetailReadonly {

    void setName(String name);

}
