package to.lova.spring.blaze.misc.model;

import com.blazebit.persistence.view.EntityView;

@EntityView(Customer.class)
public interface CustomerDetailReadonly extends AbstractCustomerDetail {

    boolean isTop();

}
