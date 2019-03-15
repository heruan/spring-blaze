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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;

import com.blazebit.persistence.view.EntityViewManager;

import to.lova.spring.blaze.model.Article;
import to.lova.spring.blaze.model.Article_;
import to.lova.spring.blaze.model.Customer;
import to.lova.spring.blaze.model.HotspotConfiguration;
import to.lova.spring.blaze.model.LocalizedString_;
import to.lova.spring.blaze.model.Person;
import to.lova.spring.blaze.model.ServiceContractFilter;
import to.lova.spring.blaze.model.ServiceContract_;
import to.lova.spring.blaze.model.ServiceItem_;
import to.lova.spring.blaze.model.TicketStatus;
import to.lova.spring.blaze.model.TicketStatus_;
import to.lova.spring.blaze.model.wiki.ArticleDetail;
import to.lova.spring.blaze.repository.ArticleRepository;
import to.lova.spring.blaze.repository.ArticleViewRepository;
import to.lova.spring.blaze.repository.ConfigurationRepository;
import to.lova.spring.blaze.repository.ConfigurationViewRepository;
import to.lova.spring.blaze.repository.CustomerDetailRepository;
import to.lova.spring.blaze.repository.CustomerSummaryRepository;
import to.lova.spring.blaze.repository.PersonRepository;
import to.lova.spring.blaze.repository.PersonViewRepository;
import to.lova.spring.blaze.repository.ServiceContractRepository;
import to.lova.spring.blaze.repository.ServiceItemRepository;
import to.lova.spring.blaze.repository.TicketDetailRepository;
import to.lova.spring.blaze.view.LocalizedStringView;
import to.lova.spring.blaze.view.PersonView;
import to.lova.spring.blaze.view.StatusDetail;
import to.lova.spring.blaze.view.StatusDetailRepository;
import to.lova.spring.blaze.view.StatusItem;
import to.lova.spring.blaze.view.TicketDetailUpdatable;

@DataJpaTest
@ContextConfiguration(classes = BlazeConfiguration.class)
public class SpringBlazeApplicationTests {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private EntityViewManager evm;

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
        var title = a1.getTitle().getLocalizedValues();
        title.put(Locale.ENGLISH, "English");
        title.put(Locale.ITALIAN, "Italiano");
        title.put(Locale.GERMAN, "Deutsch");
        var content = a1.getTitle().getLocalizedValues();
        content.put(Locale.ENGLISH, "English");
        content.put(Locale.ITALIAN, "Italiano");
        content.put(Locale.GERMAN, "Deutsch");
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
    public void testFindByAuthorView(
            @Autowired ArticleViewRepository repository,
            @Autowired PersonViewRepository personRepository) {
        var author = personRepository.getOne(this.article.getAuthor().getId());
        var articles = repository.findByAuthor(author);
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

    @Test
    public void testManyToManyInverseJoin(
            @Autowired ServiceItemRepository repository) {
        repository.findAll((r, q, b) -> {
            var sq = q.subquery(Boolean.class);
            var contract = sq.correlate(r).join(ServiceItem_.serviceContracts)
                .get(ServiceContract_.id);
            sq.select(b.literal(true)).where(b.equal(contract, "FOO"));
            return b.exists(sq);
        });
    }

    @Test
    public void createTicketFromView(
            @Autowired TicketDetailRepository ticketDetailRepository) {
        var ticket = this.evm.create(TicketDetailUpdatable.class);
        ticketDetailRepository.save(ticket);
        ticketDetailRepository.getOne(ticket.getNumber());
    }

    @Test
    public void testLocalizedStringView(
            @Autowired ArticleViewRepository articleRepository) {
        var title = this.evm.create(LocalizedStringView.class);
        var article = articleRepository.getOne(this.article.getId());
        title.getLocalizedValues().put(Locale.ENGLISH, "eng");
        title.getLocalizedValues().put(Locale.ITALIAN, "ita");
        article.setTitle(title);
        articleRepository.save(article);
    }

    @Test
    public void testArticleLocalizedWithJoin(
            @Autowired ArticleRepository repository) {
        Specification<Article> specification = (root, query, builder) -> {
            var path = root.join(Article_.title)
                .join(LocalizedString_.localizedValues);
            return builder.like(builder.upper(path), "%I%");
        };
        var count = repository
            .findAll(specification, Locale.ITALIAN, Locale.ENGLISH).size();
        assertEquals(1, count);
    }

    @Test
    public void testArticleLocalizedWithSubquery(
            @Autowired ArticleRepository repository) {
        Specification<Article> specification = (root, query, builder) -> {
            var subquery = query.subquery(Boolean.class);
            var path = subquery.correlate(root).join(Article_.title)
                .join(LocalizedString_.localizedValues);
            var predicate = builder.like(builder.upper(path), "%I%");
            return builder.exists(subquery.where(predicate));
        };
        var count = repository
            .findAll(specification, Locale.ITALIAN, Locale.ENGLISH).size();
        assertEquals(1, count);
    }

    @Test
    public void testStatusWithNextView(
            @Autowired StatusDetailRepository repository) {
        var s1 = this.em.persist(new TicketStatus("foo"));
        var s2 = this.em.persist(new TicketStatus("bar"));
        var s3 = this.em.persist(new TicketStatus("baz"));
        s3.getNext().add(s1);
        s3.getNext().add(s2);
        this.em.persistAndFlush(s3);

        repository.findAll((r, q, b) -> {
            var sq = q.subquery(TicketStatus.class);
            var sqRoot = sq.from(TicketStatus.class);
            var next = sqRoot.join(TicketStatus_.next);
            sq.select(next)
                .where(b.equal(sqRoot.get(TicketStatus_.id), s3.getId()));
            return r.in(sq);
        });
    }

    @Test
    public void testAddStatusSubtype(
            @Autowired StatusDetailRepository repository) {
        var s1 = this.em.persist(new TicketStatus("foo"));
        var s2 = this.em.persist(new TicketStatus("bar"));
        var s1Id = this.em.persistAndGetId(s1);
        var s2Id = this.em.persistAndGetId(s2);

        var entityManager = this.em.getEntityManager();

        var detail = this.evm.find(entityManager, StatusDetail.class, s1Id);
        var item = this.evm.find(entityManager, StatusItem.class, s2Id);
        detail.getNext().add(item);
    }

    @Test
    public void testOptimisticLocking(
            @Autowired ArticleViewRepository repository) {
        var view = repository.findById(this.article.getId()).get();
        view.setSlug("foo");
        repository.save(view);
    }

    @Test
    public void testWikiArticleView() {
        var view = this.evm.create(ArticleDetail.class);
        view.setSlug("foo");
        this.evm.update(this.em.getEntityManager(), view);
    }

}
