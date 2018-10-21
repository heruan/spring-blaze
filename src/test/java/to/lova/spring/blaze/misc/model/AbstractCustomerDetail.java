package to.lova.spring.blaze.misc.model;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.EntityViewInheritance;

@EntityView(AbstractCustomer.class)
@EntityViewInheritance({ CustomerDetail.class, ShippingAddressDetail.class })
public interface AbstractCustomerDetail extends AbstractCustomerBase {

    ServiceDetailView getServiceDetail();

}
