/*-
 * Copyright 2017-2018 Axians SAIV S.p.A.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
-*/
package to.lova.spring.blaze.view;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.Mapping;
import com.blazebit.persistence.view.UpdatableEntityView;

import to.lova.spring.blaze.entity.HotspotConfiguration;
import to.lova.spring.blaze.entity.HotspotConfiguration.LoginConfiguration;
import to.lova.spring.blaze.entity.HotspotConfiguration.LoginConfiguration.LoginMode;
import to.lova.spring.blaze.entity.HotspotConfiguration.LoginConfiguration.PasswordType;
import to.lova.spring.blaze.entity.HotspotConfiguration.PdfConfiguration;
import to.lova.spring.blaze.entity.HotspotConfiguration.PrivacyConfiguration;
import to.lova.spring.blaze.entity.HotspotConfiguration.TrendooConfiguration;

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
