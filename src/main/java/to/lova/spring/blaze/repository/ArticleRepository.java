package to.lova.spring.blaze.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blazebit.persistence.spring.data.repository.EntityViewSpecificationExecutor;

import to.lova.spring.blaze.entity.Article;
import to.lova.spring.blaze.view.ArticleView;

public interface ArticleRepository extends JpaRepository<Article, Long>,
        EntityViewSpecificationExecutor<ArticleView, Article> {

    boolean existsByAuthorName(String name);

    long countByAuthorName(String name);

    @Query("select a from Article a")
    List<Article> foo();

}
