package to.lova.spring.blaze.model;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;

@Entity
public class TicketStatus extends LocalizedEntity
        implements Comparable<TicketStatus> {

    private boolean initial;

    private boolean active;

    private boolean assigneeRequired;

    private boolean activityRequired;

    private boolean scheduledActivityRequired;

    private boolean publicCommentRequired;

    private boolean appliedChangeRequired;

    private boolean incidentReportRequired;

    private String theme;

    public TicketStatus(String name) {
        this.setName(name);
    }

    @OrderBy(LocalizedEntity_.NAME)
    @ManyToMany
    private SortedSet<TicketStatus> next = new TreeSet<>();

    public SortedSet<TicketStatus> getNext() {
        return this.next;
    }

    @Override
    public int compareTo(TicketStatus o) {
        return Comparator.comparing(TicketStatus::getName).compare(this, o);
    }

}
