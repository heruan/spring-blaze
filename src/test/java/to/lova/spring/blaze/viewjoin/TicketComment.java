package to.lova.spring.blaze.viewjoin;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import to.lova.spring.blaze.collection.model.User;

@Entity
public class TicketComment implements Serializable {

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

    @OneToMany(mappedBy = "entity")
    private Set<TicketCommentSeenByUser> seen = new HashSet<>();

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

    public Set<TicketCommentSeenByUser> getSeen() {
        return this.seen;
    }

}
