package to.lova.spring.blaze.model.ticket.view;

import com.blazebit.persistence.view.EntityView;

import to.lova.spring.blaze.model.ticket.entity.TicketStatus;

@EntityView(TicketStatus.class)
public interface StatusItem extends StatusBase {

}
