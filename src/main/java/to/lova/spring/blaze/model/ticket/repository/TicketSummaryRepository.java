package to.lova.spring.blaze.model.ticket.repository;

import java.util.List;
import java.util.Locale;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.blazebit.persistence.spring.data.annotation.OptionalParam;
import com.blazebit.persistence.spring.data.repository.EntityViewSpecificationExecutor;

import to.lova.spring.blaze.model.common.entity.User;
import to.lova.spring.blaze.model.ticket.entity.Ticket;
import to.lova.spring.blaze.model.ticket.view.TicketSummary;

public interface TicketSummaryRepository
        extends JpaRepository<TicketSummary, Long>,
        EntityViewSpecificationExecutor<TicketSummary, Ticket> {

    @Transactional(readOnly = true)
    List<TicketSummary> findAll(@OptionalParam("observer") User observer,
            @OptionalParam("locale") Locale locale,
            @OptionalParam("defaultLocale") Locale defaultLocale);

    @Transactional(readOnly = true)
    List<TicketSummary> findAll(Specification<Ticket> spec,
            @OptionalParam("observer") User observer);

    @Transactional(readOnly = true)
    List<TicketSummary> findAll(Specification<Ticket> spec,
            @OptionalParam("observer") User observer, Pageable pageable);

}
