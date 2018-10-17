package to.lova.spring.blaze.misc.model;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.Mapping;
import com.blazebit.persistence.view.UpdatableEntityView;

@EntityView(ShippingAddress.class)
@UpdatableEntityView
public interface ShippingAddressDetail extends AbstractCustomerDetail {

    @Mapping("customer.top")
    boolean isTop();

    CustomerDetail getCustomer();

}
