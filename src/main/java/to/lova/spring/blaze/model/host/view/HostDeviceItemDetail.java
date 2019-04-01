package to.lova.spring.blaze.model.host.view;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.EntityViewInheritance;
import com.blazebit.persistence.view.IdMapping;

import to.lova.spring.blaze.model.host.entity.HostDeviceItem;

@EntityView(HostDeviceItem.class)
@EntityViewInheritance
public interface HostDeviceItemDetail {

    @IdMapping
    Long getId();

}
