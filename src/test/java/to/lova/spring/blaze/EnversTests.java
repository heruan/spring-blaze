package to.lova.spring.blaze;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.blazebit.persistence.view.EntityViewManager;

import to.lova.spring.blaze.model.article.entity.Article;
import to.lova.spring.blaze.model.article.entity.Person;
import to.lova.spring.blaze.model.article.repository.ArticleRepository;
import to.lova.spring.blaze.model.article.repository.ArticleViewRepository;
import to.lova.spring.blaze.model.article.repository.PersonRepository;
import to.lova.spring.blaze.model.common.entity.User;
import to.lova.spring.blaze.model.common.repository.UserRepository;
import to.lova.spring.blaze.model.ticket.repository.TicketDetailRepository;
import to.lova.spring.blaze.model.ticket.repository.TicketSummaryRepository;
import to.lova.spring.blaze.model.ticket.view.TicketDetailUpdatable;

@DataJpaTest
@ContextConfiguration(classes = BlazeConfiguration.class)
public class EnversTests {

    @Autowired
    private EntityViewManager evm;

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void articleViewHasEnversMetadata(
            @Autowired PersonRepository personRepository,
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleViewRepository repository) {
        var p1 = new Person();
        p1.setName("Giovanni");
        personRepository.save(p1);

        var a1 = new Article();
        a1.setAuthor(p1);
        var title = a1.getTitle().getLocalizedValues();
        title.put(Locale.ENGLISH, "English");
        title.put(Locale.ITALIAN, "Italiano");
        title.put(Locale.GERMAN, "Deutsch");
        var content = a1.getTitle().getLocalizedValues();
        content.put(Locale.ENGLISH, "English");
        content.put(Locale.ITALIAN, "Italiano");
        content.put(Locale.GERMAN, "Deutsch");
        var id = articleRepository.saveAndFlush(a1).getId();

        var view = repository.getOne(id);

        var metadata = view.getCreationMetadata();
        assertNotNull(metadata);

        var timestamp = metadata.getTimestamp();
        assertNotNull(timestamp);
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void ticketSummaryHasEnversMetadata(
            @Autowired TicketDetailRepository ticketDetailRepository,
            @Autowired TicketSummaryRepository ticketSummaryRepository,
            @Autowired UserRepository userRepository) {
        var user = userRepository.save(new User());
        var ticket = this.evm.create(TicketDetailUpdatable.class);
        var number = ticketDetailRepository.saveAndFlush(ticket).getNumber();

        var summary = ticketSummaryRepository.findByNumber(number, user)
                .orElseThrow();

        var metadata = summary.getCreationMetadata();
        assertNotNull(metadata);

        var timestamp = metadata.getTimestamp();
        assertNotNull(timestamp);
    }

}
