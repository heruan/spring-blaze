package to.lova.spring.blaze.viewjoin;

import java.util.List;
import java.util.Locale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.blazebit.persistence.spring.data.annotation.OptionalParam;

import to.lova.spring.blaze.collection.model.User;

public interface TicketSummaryRepository
        extends JpaRepository<TicketSummary, String> {

    @Transactional(readOnly = true)
    List<TicketSummary> findAll(@OptionalParam("observer") User observer,
            @OptionalParam("locale") Locale locale,
            @OptionalParam("defaultLocale") Locale defaultLocale);

}
