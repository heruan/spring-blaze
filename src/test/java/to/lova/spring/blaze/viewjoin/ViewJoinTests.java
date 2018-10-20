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

    @Test
    public void testTicketSummarySpecification(
            @Autowired TicketSummaryRepository ticketRepository) {
        User u = new User();
        u.setName("Foo");
        final var user = this.em.persistFlushFind(u);
        ticketRepository.findAll((root, query, criteriaBuilder) -> {
            var sq = query.subquery(Boolean.class);
            var observer = sq.correlate(root).join(Ticket_.seen)
                    .get(TicketSeenByUser_.observer);
            var ticketSeen = criteriaBuilder
                    .exists(sq.where(criteriaBuilder.equal(observer, user)))
                    .not();

            var commentCountSubquery = query.subquery(Long.class);
            commentCountSubquery.correlate(root).join(Ticket_.comments);
            var commentCount = criteriaBuilder.count(commentCountSubquery);

            var seenCommentCountSubquery = query.subquery(Long.class);
            var commentPath = seenCommentCountSubquery.correlate(root)
                    .join(Ticket_.comments);
            var commentObserverPath = commentPath.join(TicketComment_.seen)
                    .get(TicketCommentSeenByUser_.observer);
            var seenCommentCount = criteriaBuilder
                    .count(seenCommentCountSubquery.where(
                            criteriaBuilder.equal(commentObserverPath, user)));

            var hasUnseenComments = criteriaBuilder.ge(
                    criteriaBuilder.diff(
                            commentCountSubquery.select(commentCount),
                            seenCommentCountSubquery.select(seenCommentCount)),
                    0);

            return criteriaBuilder.or(ticketSeen, hasUnseenComments);
        });
    }

}
