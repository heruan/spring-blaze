package to.lova.spring.blaze.model.article.repository;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blazebit.persistence.spring.data.annotation.OptionalParam;
import com.blazebit.persistence.spring.data.repository.EntityViewSpecificationExecutor;

import to.lova.spring.blaze.model.article.entity.Article;
import to.lova.spring.blaze.model.article.view.ArticleLocalized;

public interface ArticleRepository extends JpaRepository<Article, Long>,
        EntityViewSpecificationExecutor<ArticleLocalized, Article> {

    boolean existsByAuthorName(String name);

    long countByAuthorName(String name);

    @Query("select a from Article a")
    List<Article> foo();

    List<ArticleLocalized> findAll(Specification<Article> specification,
            @OptionalParam("locale") Locale locale,
            @OptionalParam("defaultLocale") Locale defaultLocale);

    Optional<ArticleLocalized> findOne(Specification<Article> specification,
            @OptionalParam("locale") Locale locale,
            @OptionalParam("defaultLocale") Locale defaultLocale);

}
