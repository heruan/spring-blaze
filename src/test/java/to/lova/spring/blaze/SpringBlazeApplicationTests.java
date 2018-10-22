package to.lova.spring.blaze;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import com.blazebit.persistence.view.EntityViewManager;

import to.lova.spring.blaze.model.Article;
import to.lova.spring.blaze.model.Customer;
import to.lova.spring.blaze.model.HotspotConfiguration;
import to.lova.spring.blaze.model.Person;
import to.lova.spring.blaze.model.ServiceContractFilter;
import to.lova.spring.blaze.repository.ArticleRepository;
import to.lova.spring.blaze.repository.ArticleViewRepository;
import to.lova.spring.blaze.repository.ConfigurationRepository;
import to.lova.spring.blaze.repository.ConfigurationViewRepository;
import to.lova.spring.blaze.repository.CustomerDetailRepository;
import to.lova.spring.blaze.repository.CustomerSummaryRepository;
import to.lova.spring.blaze.repository.PersonRepository;
import to.lova.spring.blaze.repository.PersonViewRepository;
import to.lova.spring.blaze.repository.ServiceContractRepository;
import to.lova.spring.blaze.view.PersonView;

@DataJpaTest
@ContextConfiguration(classes = BlazeConfiguration.class)
public class SpringBlazeApplicationTests {

    @Autowired
    private TestEntityManager em;

    private CriteriaBuilder cb;

    private Article article;

    @BeforeEach
    public void populateRepository() {
        this.cb = this.em.getEntityManager().getCriteriaBuilder();
        var p1 = new Person();
        p1.setName("Giovanni");
        this.em.persist(p1);

        var a1 = new Article();
        a1.setAuthor(p1);
        this.article = this.em.persist(a1);

        this.em.flush();
        this.em.clear();
    }

    @Test
    public void testFindAll(@Autowired ArticleRepository repository) {
        var articles = repository.findAll();
        assertEquals(1, articles.size());
    }

    @Test
    public void testRepositorySave(@Autowired ArticleRepository repository) {
        var a1 = new Article();
        repository.save(a1);
        assertNotNull(a1.getId());
    }

    @Test
    public void testFindAllViews(@Autowired ArticleViewRepository repository) {
        var articles = repository.findAll();
        assertEquals(1, articles.size());
    }

    @Test
    public void testFindViewById(@Autowired ArticleViewRepository repository) {
        var view = repository.findById(this.article.getId());
        assertTrue(view.isPresent());
    }

    @Test
    public void testCreatableEntityViewSave(
            @Autowired PersonViewRepository personRepository,
            @Autowired EntityViewManager evm) {
        var person = evm.create(PersonView.class);
        person.setName("Foo");
        personRepository.save(person);

        var name = personRepository.findById(person.getId()).orElseThrow()
                .getName();
        assertEquals("Foo", name);
    }

    @Test
    public void testUpdatableEntityViewSave(
            @Autowired ArticleViewRepository repository) {
        var view = repository.findById(this.article.getId()).orElseThrow();
        view.getAuthor().setName("Foo");
        repository.saveAndFlush(view);

        var name = repository.findById(this.article.getId()).orElseThrow()
                .getAuthor().getName();
        assertEquals("Foo", name);
    }

    @Test
    public void testCorrelatedSubquery(
            @Autowired PersonViewRepository repository) {
        repository.findAll((root, query, builder) -> {
            Subquery<Article> subquery = query.subquery(Article.class);
            Root<Person> correlation = subquery.correlate(root);
            Join<Person, Article> articles = correlation.join("articles");
            Predicate predicate = builder.like(articles.get("title"), "FOO");
            return builder.exists(subquery.select(articles).where(predicate));
        });
    }

    @Test
    public void testExistsByQuery(
            @Autowired ArticleRepository articleRepository) {
        var actual = articleRepository.existsByAuthorName("Giovanni");
        assertTrue(actual);
    }

    @Test
    public void testCountByQuery(@Autowired PersonRepository repository) {
        var actual = repository.countByName("Giovanni");
        assertEquals(1, actual);
    }

    @Test
    public void testFindOne(@Autowired ArticleRepository repository) {
        var view = repository.findOne((root, query, builder) -> builder
                .equal(root.get("id"), this.article.getId()));
        assertNotNull(view);
    }

    @Test
    public void testNestedEmbeddables(
            @Autowired ConfigurationRepository repository,
            @Autowired ConfigurationViewRepository viewRepository) {
        var c = repository.save(new HotspotConfiguration());
        var v = repository.findOne((root, query, builder) -> builder
                .equal(root.get("id"), c.getId()));
        this.em.flush();
        this.em.clear();
        v.getLoginConfiguration().getWelcomeMessage().getLocalizedValues()
                .put(Locale.ENGLISH, "foo");
        v.getLoginConfiguration().getWelcomeMessage().getLocalizedValues()
                .put(Locale.ITALIAN, "foo");
        viewRepository.save(v);
        this.em.flush();
        this.em.clear();
        repository.findOne((root, query, builder) -> builder
                .equal(root.get("id"), c.getId()));
    }

    @Test
    public void testBooleanMapping(
            @Autowired CustomerSummaryRepository repository) {
        repository.findAll();
    }

    @Test
    public void testCriteriaJoin(
            @Autowired ServiceContractRepository repository) {
        var filter = new ServiceContractFilter();
        filter.setCustomerCity("foo");
        repository.count(filter);
        repository.findAll(filter);
    }

    @Test
    public void testUpdatableNestedView(
            @Autowired CustomerDetailRepository customerRepository,
            @Autowired EntityViewManager evm) {
        var id = this.em.persistAndGetId(new Customer(), Long.class);
        var customer = customerRepository.findById(id).orElseThrow();
        customer.getServiceDetail().setServiceHours("foo");
        customerRepository.saveAndFlush(customer);
        var detail = customerRepository.findById(id).orElseThrow()
                .getServiceDetail();
        assertEquals("foo", detail.getServiceHours());
    }

}
