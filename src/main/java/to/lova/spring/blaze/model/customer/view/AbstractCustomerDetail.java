package to.lova.spring.blaze.model.customer.view;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.EntityViewInheritance;

import to.lova.spring.blaze.model.customer.entity.AbstractCustomer;

@EntityView(AbstractCustomer.class)
@EntityViewInheritance({ CustomerDetail.class, ShippingAddressDetail.class })
public interface AbstractCustomerDetail extends AbstractCustomerBase {

    ServiceDetailView getServiceDetail();

}
