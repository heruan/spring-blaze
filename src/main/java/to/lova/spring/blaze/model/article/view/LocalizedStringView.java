package to.lova.spring.blaze.model.article.view;

import java.util.Locale;
import java.util.Map;

import com.blazebit.persistence.view.CreatableEntityView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.UpdatableEntityView;
import com.blazebit.persistence.view.UpdatableMapping;

import to.lova.spring.blaze.model.article.entity.LocalizedString;

@EntityView(LocalizedString.class)
@CreatableEntityView
@UpdatableEntityView
public interface LocalizedStringView {

    @UpdatableMapping
    Map<Locale, String> getLocalizedValues();

}
