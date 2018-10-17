package to.lova.spring.blaze.misc.model;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.EntityViewInheritance;
import com.blazebit.persistence.view.UpdatableEntityView;
import com.blazebit.persistence.view.UpdatableMapping;

@EntityView(AbstractCustomer.class)
@UpdatableEntityView
@EntityViewInheritance
public interface AbstractCustomerDetail extends AbstractCustomerBase {

    @UpdatableMapping
    ServiceDetail getServiceDetail();

}
