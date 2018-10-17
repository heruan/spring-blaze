package to.lova.spring.blaze.misc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.OrderColumn;

@Embeddable
public class ServiceDetail implements Serializable {

    private String serviceHours;

    @OrderColumn
    @ElementCollection
    private List<SwitchboardAddress> switchboardAddresses = new ArrayList<>();

    @OrderColumn
    @ElementCollection
    private List<SwitchboardInterface> switchboardInterfaces = new ArrayList<>();

    @Override
    public int hashCode() {
        return Objects.hash(this.serviceHours, this.switchboardAddresses,
                this.switchboardInterfaces);
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
        return Objects.equals(this.serviceHours, other.serviceHours)
                && Objects.equals(this.switchboardAddresses,
                        other.switchboardAddresses)
                && Objects.equals(this.switchboardInterfaces,
                        other.switchboardInterfaces);
    }

}
