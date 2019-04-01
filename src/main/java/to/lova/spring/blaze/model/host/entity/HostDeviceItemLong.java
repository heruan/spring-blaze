package to.lova.spring.blaze.model.host.entity;

import java.time.Instant;
import java.util.Map;

import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("long")
public class HostDeviceItemLong extends HostDeviceItem {

    @ElementCollection
    Map<Instant, Long> values;

}
