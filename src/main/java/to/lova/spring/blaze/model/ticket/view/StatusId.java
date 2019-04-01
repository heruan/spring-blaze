package to.lova.spring.blaze.model.ticket.view;

import com.blazebit.persistence.view.EntityView;

import to.lova.spring.blaze.model.article.view.LocalizedEntityId;
import to.lova.spring.blaze.model.ticket.entity.TicketStatus;

@EntityView(TicketStatus.class)
public interface StatusId extends LocalizedEntityId<TicketStatus> {

}
