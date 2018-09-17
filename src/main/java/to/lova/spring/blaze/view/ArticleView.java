package to.lova.spring.blaze.view;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.UpdatableEntityView;
import com.blazebit.persistence.view.UpdatableMapping;

import to.lova.spring.blaze.entity.Article;

@EntityView(Article.class)
@UpdatableEntityView
public interface ArticleView {

    @IdMapping
    Long getId();

    @UpdatableMapping
    PersonView getAuthor();

    void setAuthor(PersonView person);

}
