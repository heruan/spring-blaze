package to.lova.spring.blaze.view;

import com.blazebit.persistence.view.EntityView;

import to.lova.spring.blaze.model.TicketStatus;

@EntityView(TicketStatus.class)
public interface StatusId extends LocalizedEntityId<TicketStatus> {

}
