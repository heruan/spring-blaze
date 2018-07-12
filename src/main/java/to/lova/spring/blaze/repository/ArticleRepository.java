package to.lova.spring.blaze.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import to.lova.spring.blaze.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
	
}
