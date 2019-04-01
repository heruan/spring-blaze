package to.lova.spring.blaze.model.ticket.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class TicketCommented extends TicketHistoryItem {

    @OneToOne
    private TicketComment comment;

    public TicketComment getComment() {
        return this.comment;
    }

    public void setComment(TicketComment comment) {
        this.comment = comment;
    }

}
