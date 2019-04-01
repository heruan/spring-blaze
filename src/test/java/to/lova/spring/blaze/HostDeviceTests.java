package to.lova.spring.blaze;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import com.blazebit.persistence.view.EntityViewManager;

import to.lova.spring.blaze.model.host.view.HostDeviceWithItems;

@DataJpaTest
@ContextConfiguration(classes = BlazeConfiguration.class)
public class HostDeviceTests {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private EntityViewManager evm;

    @Test
    public void lastValueProviderTest() {
        this.evm.find(this.em.getEntityManager(), HostDeviceWithItems.class,
                123);
    }

}
