package to.lova.spring.blaze.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blazebit.persistence.spring.data.repository.EntityViewSpecificationExecutor;

import to.lova.spring.blaze.model.Ticket;
import to.lova.spring.blaze.view.TicketDetailUpdatable;

public interface TicketDetailRepository
        extends JpaRepository<TicketDetailUpdatable, Long>,
        EntityViewSpecificationExecutor<TicketDetailUpdatable, Ticket> {

}
