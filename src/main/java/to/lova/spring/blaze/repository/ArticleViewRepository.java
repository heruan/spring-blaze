package to.lova.spring.blaze.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import to.lova.spring.blaze.view.ArticleView;

public interface ArticleViewRepository
        extends JpaRepository<ArticleView, Long> {

}
