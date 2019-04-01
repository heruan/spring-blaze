package to.lova.spring.blaze.model.ticket.view;

import java.time.Instant;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.EntityViewInheritance;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.UpdatableEntityView;

import to.lova.spring.blaze.model.common.view.UserBase;
import to.lova.spring.blaze.model.ticket.entity.TicketHistoryItem;

@EntityView(TicketHistoryItem.class)
@UpdatableEntityView
@EntityViewInheritance
public interface TicketHistoryDetail {

    @IdMapping
    Long getId();

    Instant getCreated();

    void setCreated(Instant created);

    UserBase getAuthor();

    void setAuthor(UserBase author);

}
