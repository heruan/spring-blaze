package to.lova.spring.blaze.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import to.lova.spring.blaze.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    long countByAuthorName(String name);

    @Query("select a from Article a")
    List<Article> foo();

}
