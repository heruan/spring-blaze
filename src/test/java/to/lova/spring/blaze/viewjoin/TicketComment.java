package to.lova.spring.blaze.viewjoin;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import to.lova.spring.blaze.collection.model.User;

@Entity
public class TicketComment {

    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    Ticket ticket;

    @ManyToOne
    User author;

    @ManyToOne
    User assignee;

    Instant creationInstant = Instant.now();

    public Ticket getTicket() {
        return this.ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getAssignee() {
        return this.assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public Long getId() {
        return this.id;
    }

    public Instant getCreationInstant() {
        return this.creationInstant;
    }

}
