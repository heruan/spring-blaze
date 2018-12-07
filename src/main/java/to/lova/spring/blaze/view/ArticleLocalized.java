package to.lova.spring.blaze.view;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.Mapping;

import to.lova.spring.blaze.model.Article;

@EntityView(Article.class)
public interface ArticleLocalized {

    @IdMapping
    Long getId();

    @Mapping("coalesce(title.localizedValues[:locale], title.localizedValues[:defaultLocale])")
    String getTitle();

    @Mapping("coalesce(content.localizedValues[:locale], content.localizedValues[:defaultLocale])")
    String getContent();

}
