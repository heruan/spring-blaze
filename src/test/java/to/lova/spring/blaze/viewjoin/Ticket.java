package to.lova.spring.blaze.viewjoin;

import java.time.Instant;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import to.lova.spring.blaze.collection.model.User;
import to.lova.spring.blaze.misc.model.AbstractCustomer;

@Entity
public class Ticket {

    @Id
    @GeneratedValue
    Long id;

    String subject;

    @ManyToOne
    AbstractCustomer customer;

    @ManyToOne
    User author;

    @ManyToOne
    User assignee;

    boolean active;

    Instant creationInstant = Instant.now();

    @OrderBy(TicketComment_.CREATION_INSTANT)
    @OneToMany(mappedBy = "ticket")
    SortedSet<TicketComment> comments = new TreeSet<>();

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public AbstractCustomer getCustomer() {
        return this.customer;
    }

    public void setCustomer(AbstractCustomer customer) {
        this.customer = customer;
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

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Instant getCreationInstant() {
        return this.creationInstant;
    }

    public Long getId() {
        return this.id;
    }

    public SortedSet<TicketComment> getComments() {
        return this.comments;
    }

}
