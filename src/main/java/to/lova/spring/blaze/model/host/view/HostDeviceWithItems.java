package to.lova.spring.blaze.model.host.view;

import java.util.Set;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;

import to.lova.spring.blaze.model.host.entity.HostDevice;

@EntityView(HostDevice.class)
public interface HostDeviceWithItems {

    @IdMapping
    Long getId();

    Set<HostDeviceItemDetail> getItems();

}
