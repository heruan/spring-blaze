package to.lova.spring.blaze.view;

import com.blazebit.persistence.view.Mapping;

import to.lova.spring.blaze.model.LocalizedEntity;

public interface LocalizedEntityWithTitle<T extends LocalizedEntity>
        extends LocalizedEntityBase<T> {

    @Mapping("coalesce(title.localizedValues[:locale], title.localizedValues[:defaultLocale])")
    String getTitle();

}
