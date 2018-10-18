package to.lova.spring.blaze.misc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;

import to.lova.spring.blaze.collection.model.User;

@Embeddable
public class ServiceDetail implements Serializable {

    private String serviceHours;

    private boolean active;

    @ManyToOne
    private User technician;

    public User getTechnician() {
        return this.technician;
    }

    public void setTechnician(User technician) {
        this.technician = technician;
    }

    @OrderColumn
    @ElementCollection
    private List<SwitchboardAddress> switchboardAddresses = new ArrayList<>();

    @OrderColumn
    @ElementCollection
    private List<SwitchboardInterface> switchboardInterfaces = new ArrayList<>();

    public String getServiceHours() {
        return this.serviceHours;
    }

    public void setServiceHours(String serviceHours) {
        this.serviceHours = serviceHours;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<SwitchboardAddress> getSwitchboardAddresses() {
        return this.switchboardAddresses;
    }

    public List<SwitchboardInterface> getSwitchboardInterfaces() {
        return this.switchboardInterfaces;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.active, this.serviceHours,
                this.switchboardAddresses, this.switchboardInterfaces,
                this.technician);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ServiceDetail)) {
            return false;
        }
        ServiceDetail other = (ServiceDetail) obj;
        return this.active == other.active
                && Objects.equals(this.serviceHours, other.serviceHours)
                && Objects.equals(this.switchboardAddresses,
                        other.switchboardAddresses)
                && Objects.equals(this.switchboardInterfaces,
                        other.switchboardInterfaces)
                && Objects.equals(this.technician, other.technician);
    }

}
