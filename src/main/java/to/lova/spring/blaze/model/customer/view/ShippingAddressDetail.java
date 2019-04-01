package to.lova.spring.blaze.model.customer.view;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.Mapping;
import com.blazebit.persistence.view.UpdatableEntityView;

import to.lova.spring.blaze.model.customer.entity.ShippingAddress;

@EntityView(ShippingAddress.class)
@UpdatableEntityView
public interface ShippingAddressDetail extends AbstractCustomerDetail {

    @Mapping("customer.top")
    boolean isTop();

    CustomerDetailReadonly getCustomer();

}
