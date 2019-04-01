package to.lova.spring.blaze.model.article.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import to.lova.spring.blaze.model.article.view.ArticleView;
import to.lova.spring.blaze.model.article.view.PersonView;

public interface ArticleViewRepository
        extends JpaRepository<ArticleView, Long> {

    List<ArticleView> findByAuthor(PersonView author);

}
