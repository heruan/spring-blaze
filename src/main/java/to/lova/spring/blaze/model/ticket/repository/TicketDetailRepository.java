package to.lova.spring.blaze.model.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blazebit.persistence.spring.data.repository.EntityViewSpecificationExecutor;

import to.lova.spring.blaze.model.ticket.entity.Ticket;
import to.lova.spring.blaze.model.ticket.view.TicketDetailUpdatable;

public interface TicketDetailRepository
        extends JpaRepository<TicketDetailUpdatable, Long>,
        EntityViewSpecificationExecutor<TicketDetailUpdatable, Ticket> {

}
