package to.lova.spring.blaze.model.customer.view;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.Mapping;

import to.lova.spring.blaze.model.customer.entity.ShippingAddress;

@EntityView(ShippingAddress.class)
public interface ShippingAddressSummary extends AbstractCustomerSummary {

    @Mapping("customer.top")
    boolean isTop();

}
