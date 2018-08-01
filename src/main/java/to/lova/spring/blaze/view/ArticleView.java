package to.lova.spring.blaze.view;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.Mapping;

import to.lova.spring.blaze.entity.Article;

@EntityView(Article.class)
public interface ArticleView {

    Long getId();

    @Mapping("author.name")
    String getAuthorName();

}
