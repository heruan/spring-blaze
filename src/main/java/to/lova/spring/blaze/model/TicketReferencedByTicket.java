package to.lova.spring.blaze.model;

import javax.persistence.Entity;

@Entity
public class TicketReferencedByTicket extends TicketHistoryItem {

    private Ticket referencingTicket;

    public Ticket getReferencingTicket() {
        return this.referencingTicket;
    }

    public void setReferencingTicket(Ticket referencingTicket) {
        this.referencingTicket = referencingTicket;
    }

}
