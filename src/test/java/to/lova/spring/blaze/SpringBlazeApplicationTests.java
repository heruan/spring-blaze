package to.lova.spring.blaze;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import to.lova.spring.blaze.entity.Article;
import to.lova.spring.blaze.entity.Person;
import to.lova.spring.blaze.repository.ArticleRepository;
import to.lova.spring.blaze.repository.ArticleViewRepository;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BlazeConfiguration.class)
public class SpringBlazeApplicationTests {

    @Autowired
    private TestEntityManager em;

    private Article article;

    @BeforeEach
    public void populateRepository() {
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
    public void testUpdatableEntityViewSave(
            @Autowired ArticleViewRepository repository) {
        var view = repository.findById(this.article.getId()).orElseThrow();
        view.getAuthor().setName("Foo");
        repository.save(view);
    }

}
