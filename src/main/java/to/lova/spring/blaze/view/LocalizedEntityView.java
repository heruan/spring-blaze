package to.lova.spring.blaze.view;

import com.blazebit.persistence.view.EntityView;

import to.lova.spring.blaze.model.LocalizedEntity;

@EntityView(LocalizedEntity.class)
public interface LocalizedEntityView<T extends LocalizedEntity>
        extends LocalizedEntityBase<T> {

    void setName(String name);

    LocalizedStringView getTitle();

    void setTitle(LocalizedStringView title);

    LocalizedStringView getDescription();

    void setDescription(LocalizedStringView description);

}
