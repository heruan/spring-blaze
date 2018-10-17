package to.lova.spring.blaze.misc.model;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.UpdatableEntityView;

@EntityView(Customer.class)
@UpdatableEntityView
public interface CustomerDetail extends AbstractCustomerDetail {

    boolean isTop();

}
