package to.lova.spring.blaze.model.ticket.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Version;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

import to.lova.spring.blaze.model.common.entity.User;
import to.lova.spring.blaze.model.customer.entity.AbstractCustomer;

@Entity
@Audited
public class Ticket implements Serializable {

    @Id
    @GeneratedValue
    Long number;

    @Version
    Long version;

    String subject;

    @ManyToOne
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
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

    @OrderBy(TicketHistoryItem_.CREATED)
    @OneToMany(mappedBy = TicketHistoryItem_.TICKET)
    @NotAudited
    SortedSet<TicketHistoryItem> history = new TreeSet<>();

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

    public SortedSet<TicketHistoryItem> getHistory() {
        return this.history;
    }

}
