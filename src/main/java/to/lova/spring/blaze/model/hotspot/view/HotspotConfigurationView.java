package to.lova.spring.blaze.model.hotspot.view;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.Mapping;
import com.blazebit.persistence.view.UpdatableEntityView;

import to.lova.spring.blaze.model.article.view.LocalizedStringView;
import to.lova.spring.blaze.model.hotspot.entity.HotspotConfiguration;
import to.lova.spring.blaze.model.hotspot.entity.HotspotConfiguration.LoginConfiguration;
import to.lova.spring.blaze.model.hotspot.entity.HotspotConfiguration.LoginConfiguration.LoginMode;
import to.lova.spring.blaze.model.hotspot.entity.HotspotConfiguration.LoginConfiguration.PasswordType;
import to.lova.spring.blaze.model.hotspot.entity.HotspotConfiguration.PdfConfiguration;
import to.lova.spring.blaze.model.hotspot.entity.HotspotConfiguration.PrivacyConfiguration;
import to.lova.spring.blaze.model.hotspot.entity.HotspotConfiguration.TrendooConfiguration;

@EntityView(HotspotConfiguration.class)
@UpdatableEntityView
public interface HotspotConfigurationView {

    @IdMapping
    Long getId();

    String getRedirectUrl();

    void setRedirectUrl(String redirectUrl);

    String getNasIp();

    void setNasIp(String nasIp);

    int getNasPort();

    void setNasPort(int nasPort);

    int getNasCoaPort();

    void setNasCoaPort(int nasCoaPort);

    String getRadiusSecret();

    void setRadiusSecret(String radiusSecret);

    String getLogo();

    void setLogo(String logo);

    @Mapping("login")
    LoginConfigurationView getLoginConfiguration();

    @Mapping("pdf")
    PdfConfiguration getPdfConfiguration();

    @Mapping("trendoo")
    TrendooConfiguration getTrendooConfiguration();

    @Mapping("privacy")
    PrivacyConfiguration getPrivacyConfiguration();

    @EntityView(LoginConfiguration.class)
    @UpdatableEntityView
    interface LoginConfigurationView {

        LoginMode getLoginMode();

        void setLoginMode(LoginMode loginMode);

        PasswordType getPasswordType();

        void setPasswordType(PasswordType passwordType);

        int getPasswordLength();

        void setPasswordLength(int passwordLength);

        @Mapping("welcome")
        LocalizedStringView getWelcomeMessage();

        @Mapping("instruction")
        LocalizedStringView getInstructionMessage();

        @Mapping("termsShort")
        LocalizedStringView getTermsShortMessage();

        @Mapping("termsFull")
        LocalizedStringView getTermsFullMessage();

    }

}
