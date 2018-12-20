package to.lova.spring.blaze.view;

import to.lova.spring.blaze.model.TicketStatus;
import to.lova.spring.blaze.repository.LocalizedEntityViewRepository;

public interface StatusDetailRepository
        extends LocalizedEntityViewRepository<StatusDetail, TicketStatus> {

}
