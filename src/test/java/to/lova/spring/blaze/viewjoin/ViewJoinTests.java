package to.lova.spring.blaze.viewjoin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViewSetting;

import to.lova.spring.blaze.BlazeConfiguration;

@DataJpaTest
@ContextConfiguration(classes = BlazeConfiguration.class)
public class ViewJoinTests {

    @Autowired
    TestEntityManager em;

    @Test
    public void testNestedSubviewWithAggregates(
            @Autowired CriteriaBuilderFactory cbf,
            @Autowired EntityViewManager evm) {
        var ticket = evm.create(TicketDetail.class);
        evm.update(this.em.getEntityManager(), ticket);

        var comment = evm.create(TicketCommentDetail.class);
        comment.setTicket(ticket);
        evm.update(this.em.getEntityManager(), comment);

        var cb = cbf.create(this.em.getEntityManager(), TicketComment.class);
        var setting = EntityViewSetting.create(TicketCommentDetail.class);
        var size = evm.applySetting(setting, cb).getResultList().size();
        assertEquals(0, size);
    }

    @Test
    public void testNestedSubviewWithAggregates(
            @Autowired TicketCommentDetailRepository repository) {
        var size = repository.findByTicketId(1L).size();
        assertEquals(0, size);
    }

}
