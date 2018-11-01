package to.lova.spring.blaze.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class TicketReferencedByComment extends TicketHistoryItem {

    @ManyToOne
    private TicketComment referencingComment;

    public TicketComment getReferencingComment() {
        return this.referencingComment;
    }

    public void setReferencingComment(TicketComment referencingComment) {
        this.referencingComment = referencingComment;
    }

}
