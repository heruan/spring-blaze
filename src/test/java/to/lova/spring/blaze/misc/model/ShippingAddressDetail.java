package to.lova.spring.blaze.misc.model;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.Mapping;

@EntityView(ShippingAddress.class)
public interface ShippingAddressDetail extends AbstractCustomerDetail {

    @Mapping("customer.top")
    boolean isTop();

    CustomerDetail getCustomer();

}
