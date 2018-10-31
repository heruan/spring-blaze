package to.lova.spring.blaze.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.OrderColumn;

@Entity
public class Ticket implements Serializable {

    @Id
    @GeneratedValue
    Long number;

    String subject;

    @ManyToOne
    AbstractCustomer customer;

    @ManyToOne
    User author;

    @ManyToOne
    User assignee;

    boolean open;

    Instant creationInstant = Instant.now();

    @OrderBy(TicketComment_.CREATION_INSTANT)
    @OneToMany(mappedBy = TicketComment_.TICKET)
    SortedSet<TicketComment> comments = new TreeSet<>();

    @OrderColumn
    @ElementCollection
    List<TicketHistoryItem> history = new ArrayList<>();

    @OneToMany
    private Set<User> seen = new HashSet<>();

    public Long getNumber() {
        return this.number;
    }

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

    public boolean isOpen() {
        return this.open;
    }

    public void setOpen(boolean active) {
        this.open = active;
    }

    public Instant getCreationInstant() {
        return this.creationInstant;
    }

    public SortedSet<TicketComment> getComments() {
        return this.comments;
    }

    public Set<User> getSeen() {
        return this.seen;
    }

}
