package to.lova.spring.blaze.view;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.blazebit.persistence.view.CreatableEntityView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.PostCreate;
import com.blazebit.persistence.view.UpdatableEntityView;
import com.blazebit.persistence.view.UpdatableMapping;

import to.lova.spring.blaze.model.LocalizedString;

@EntityView(LocalizedString.class)
@CreatableEntityView
@UpdatableEntityView
public abstract class LocalizedStringView {

    @UpdatableMapping
    public abstract Map<Locale, String> getLocalizedValues();

    abstract void setLocalizedValues(Map<Locale, String> localizedValues);

    @PostCreate
    void init() {
        this.setLocalizedValues(new HashMap<>());
    }

}
