package to.lova.spring.blaze.misc.model;

import java.io.Serializable;
import java.util.List;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.UpdatableEntityView;
import com.blazebit.persistence.view.UpdatableMapping;

import to.lova.spring.blaze.collection.model.UserDetail;

@EntityView(ServiceDetail.class)
@UpdatableEntityView
public interface ServiceDetailView extends Serializable {

    String getServiceHours();

    void setServiceHours(String serviceHours);

    boolean isActive();

    void setActive(boolean active);

    /**
     * Gets the technician.
     *
     * @return the technician
     */
    public UserDetail getTechnician();

    /**
     * Sets the technician.
     *
     * @param technician
     *            the new technician
     */
    public void setTechnician(UserDetail technician);

    /**
     * Gets the switchboard addresses.
     *
     * @return the switchboard addresses
     */
    @UpdatableMapping
    public List<SwitchboardAddressView> getSwitchboardAddresses();

    /**
     * Gets the switchboard interfaces.
     *
     * @return the switchboard interfaces
     */
    @UpdatableMapping
    public List<SwitchboardInterfaceView> getSwitchboardInterfaces();

}
