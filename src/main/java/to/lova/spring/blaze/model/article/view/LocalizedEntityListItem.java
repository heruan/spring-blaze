package to.lova.spring.blaze.model.article.view;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.Mapping;

import to.lova.spring.blaze.model.article.entity.LocalizedEntity;

@EntityView(LocalizedEntity.class)
public interface LocalizedEntityListItem<T extends LocalizedEntity>
        extends LocalizedEntityWithTitle<T> {

    @Mapping("coalesce(description.localizedValues[:locale], description.localizedValues[:defaultLocale])")
    String getDescription();

}
