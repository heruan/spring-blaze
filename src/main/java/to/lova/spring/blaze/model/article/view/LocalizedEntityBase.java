package to.lova.spring.blaze.model.article.view;

import com.blazebit.persistence.view.EntityView;

import to.lova.spring.blaze.model.article.entity.LocalizedEntity;

@EntityView(LocalizedEntity.class)
public interface LocalizedEntityBase<T extends LocalizedEntity>
        extends LocalizedEntityId<T> {

    String getName();

}
