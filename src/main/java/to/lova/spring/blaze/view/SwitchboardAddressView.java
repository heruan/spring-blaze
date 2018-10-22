package to.lova.spring.blaze.view;

import com.blazebit.persistence.view.CreatableEntityView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.UpdatableEntityView;

import to.lova.spring.blaze.model.SwitchboardAddress;

@EntityView(SwitchboardAddress.class)
@CreatableEntityView
@UpdatableEntityView
public interface SwitchboardAddressView {

    /**
     * Gets the address.
     *
     * @return the address
     */
    public String getAddress();

    /**
     * Sets the address.
     *
     * @param address
     *            the new address
     */
    public void setAddress(String address);

    /**
     * Gets the network.
     *
     * @return the network
     */
    public String getNetwork();

    /**
     * Sets the network.
     *
     * @param network
     *            the new network
     */
    public void setNetwork(String network);

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription();

    /**
     * Sets the description.
     *
     * @param description
     *            the new description
     */
    public void setDescription(String description);

}
