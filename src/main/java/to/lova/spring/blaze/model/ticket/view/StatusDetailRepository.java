package to.lova.spring.blaze.model.ticket.view;

import to.lova.spring.blaze.model.article.repository.LocalizedEntityViewRepository;
import to.lova.spring.blaze.model.ticket.entity.TicketStatus;

public interface StatusDetailRepository
        extends LocalizedEntityViewRepository<StatusDetail, TicketStatus> {

}
