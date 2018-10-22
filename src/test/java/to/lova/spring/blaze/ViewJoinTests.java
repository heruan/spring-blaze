package to.lova.spring.blaze;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import to.lova.spring.blaze.model.Ticket;
import to.lova.spring.blaze.model.TicketComment;
import to.lova.spring.blaze.model.TicketComment_;
import to.lova.spring.blaze.model.Ticket_;
import to.lova.spring.blaze.model.User;
import to.lova.spring.blaze.repository.TicketSummaryRepository;

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
        t1.setAuthor(u1);
        t1 = this.em.persistFlushFind(t1);

        Ticket t2 = new Ticket();
        t2.setAuthor(u2);
        t2 = this.em.persistFlushFind(t2);

        TicketComment c1 = new TicketComment();
        c1.setTicket(t1);
        c1.setAuthor(u1);
        this.em.persistAndFlush(c1);

        TicketComment c2 = new TicketComment();
        c2.setTicket(t1);
        c2.setAuthor(u2);
        this.em.persistAndFlush(c2);

        var list = ticketRepository.findAll(u1, Locale.ENGLISH, Locale.ENGLISH);
        assertEquals(2, list.size());

        var id1 = list.get(0).getNumber();
        var id2 = list.get(1).getNumber();
        assertFalse(id1.equals(id2));
    }

    @Test
    public void testTicketSummarySpecification(
            @Autowired TicketSummaryRepository ticketRepository) {
        User u = new User();
        u.setName("Foo");
        final var user = this.em.persistFlushFind(u);
        ticketRepository.findAll((root, query, criteriaBuilder) -> {
            var ticketSeen = criteriaBuilder.isNotMember(user,
                    root.get(Ticket_.seen));

            var comments = root.get(Ticket_.comments);
            var commentCount = criteriaBuilder.size(comments);

            var seenCommentCountSubquery = query.subquery(Long.class);
            var commentPath = seenCommentCountSubquery.correlate(root)
                    .join(Ticket_.comments);
            var seenCommentCount = criteriaBuilder.count(commentPath);
            seenCommentCountSubquery.select(seenCommentCount)
                    .where(criteriaBuilder.isMember(user,
                            commentPath.get(TicketComment_.seen)));

            var hasUnseenComments = criteriaBuilder.ge(criteriaBuilder
                    .diff(commentCount, seenCommentCountSubquery), 0);

            return criteriaBuilder.or(ticketSeen, hasUnseenComments);
        }, user);
    }

}
