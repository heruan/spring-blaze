package to.lova.spring.blaze.viewjoin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketCommentDetailRepository
        extends JpaRepository<TicketCommentDetail, Long> {

    List<TicketCommentDetail> findByTicketId(Long id);

}
