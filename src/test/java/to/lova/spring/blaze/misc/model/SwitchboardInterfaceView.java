package to.lova.spring.blaze.misc.model;

import com.blazebit.persistence.view.CreatableEntityView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.UpdatableEntityView;

@EntityView(SwitchboardInterface.class)
@CreatableEntityView
@UpdatableEntityView
public interface SwitchboardInterfaceView {

    public String getNumber();

    /**
     * Sets the number.
     *
     * @param number
     *            the new number
     */
    public void setNumber(String number);

    /**
     * Gets the slot.
     *
     * @return the slot
     */
    public String getSlot();

    /**
     * Sets the slot.
     *
     * @param slot
     *            the new slot
     */
    public void setSlot(String slot);

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
