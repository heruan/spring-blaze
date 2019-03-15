package to.lova.spring.blaze.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import to.lova.spring.blaze.view.ArticleView;
import to.lova.spring.blaze.view.PersonView;

public interface ArticleViewRepository
        extends JpaRepository<ArticleView, Long> {

    List<ArticleView> findByAuthor(PersonView author);

}
