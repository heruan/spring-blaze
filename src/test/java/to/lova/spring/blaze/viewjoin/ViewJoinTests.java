package to.lova.spring.blaze.viewjoin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import to.lova.spring.blaze.BlazeConfiguration;
import to.lova.spring.blaze.collection.model.User;

@DataJpaTest
@ContextConfiguration(classes = BlazeConfiguration.class)
public class ViewJoinTests {

    @Autowired
    TestEntityManager em;

    @Test
    public void testTicketSummary(
            @Autowired TicketSummaryRepository ticketRepository) {
        User u1 = new User();
        u1.setName("Foo");
        u1 = this.em.persistFlushFind(u1);
        User u2 = new User();
        u2.setName("Bar");
        u2 = this.em.persistFlushFind(u2);

        Ticket t1 = new Ticket();
        t1.author = u1;
        t1 = this.em.persistFlushFind(t1);

        Ticket t2 = new Ticket();
        t2.author = u2;
        t2 = this.em.persistFlushFind(t2);

        TicketComment c1 = new TicketComment();
        c1.ticket = t1;
        c1.author = u1;
        this.em.persistAndFlush(c1);

        TicketComment c2 = new TicketComment();
        c2.ticket = t1;
        c2.author = u2;
        this.em.persistAndFlush(c2);

        var list = ticketRepository.findAll(u1, Locale.ENGLISH, Locale.ENGLISH);
        assertEquals(2, list.size());

        var id1 = list.get(0).getNumber();
        var id2 = list.get(1).getNumber();
        assertFalse(id1.equals(id2));
    }

}
