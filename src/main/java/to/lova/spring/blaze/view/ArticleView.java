package to.lova.spring.blaze.view;

import com.blazebit.persistence.view.CreatableEntityView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.UpdatableEntityView;

import to.lova.spring.blaze.model.Article;

@EntityView(Article.class)
@CreatableEntityView
@UpdatableEntityView
public interface ArticleView {

    @IdMapping
    Long getId();

    PersonView getAuthor();

    void setAuthor(PersonView person);

    LocalizedStringView getTitle();

    void setTitle(LocalizedStringView title);

    String getSlug();

    void setSlug(String slug);

}
