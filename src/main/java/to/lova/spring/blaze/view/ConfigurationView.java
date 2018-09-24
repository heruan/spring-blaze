package to.lova.spring.blaze.view;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.UpdatableEntityView;

import to.lova.spring.blaze.entity.Configuration;
import to.lova.spring.blaze.entity.Configuration.LoginConfiguration;

@EntityView(Configuration.class)
@UpdatableEntityView
public interface ConfigurationView {

    @IdMapping
    Long getId();

    LoginConfigurationView getLoginConfiguration();

    @EntityView(LoginConfiguration.class)
    @UpdatableEntityView
    interface LoginConfigurationView {

        LocalizedStringView getMessageFoo();

        LocalizedStringView getMessageBar();

    }

}
