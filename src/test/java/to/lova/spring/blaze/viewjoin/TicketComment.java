package to.lova.spring.blaze.viewjoin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import to.lova.spring.blaze.collection.model.User;

@Entity
public class TicketComment {

    @GeneratedValue
    @Id
    Long id;

    @ManyToOne
    Ticket ticket;

    @ManyToOne
    User author;

    @ManyToOne
    User assignee;

}
