package to.lova.spring.blaze.model.ticket.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import to.lova.spring.blaze.model.common.entity.User;

@Entity
public class TicketAssigneeChanged extends TicketHistoryItem {

    @ManyToOne
    private User assigneeBefore;

    @ManyToOne
    private User assigneeAfter;

    public User getAssigneeBefore() {
        return this.assigneeBefore;
    }

    public void setAssigneeBefore(User assigneeBefore) {
        this.assigneeBefore = assigneeBefore;
    }

    public User getAssigneeAfter() {
        return this.assigneeAfter;
    }

    public void setAssigneeAfter(User assigneeAfter) {
        this.assigneeAfter = assigneeAfter;
    }

}
