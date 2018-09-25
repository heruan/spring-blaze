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
package to.lova.spring.blaze.entity;

import java.io.Serializable;
import java.util.Locale;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class HotspotConfiguration extends Configuration {

    private String logo = "axians-logo.png";

    private String nasIp;

    private int nasPort;

    private int nasCoaPort;

    private String radiusIP;

    private String radiusSecret;

    private String redirectUrl = "http://www.axians.it";

    @NotNull
    @Embedded
    private LoginConfiguration login = new LoginConfiguration();

    @NotNull
    @Embedded
    private TrendooConfiguration trendoo = new TrendooConfiguration();

    @NotNull
    @Embedded
    private PdfConfiguration pdf = new PdfConfiguration();

    @NotNull
    @Embedded
    private PrivacyConfiguration privacy = new PrivacyConfiguration();

    public HotspotConfiguration() {
        super("hotspot");
    }

    public String getRedirectUrl() {
        return this.redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getNasIp() {
        return this.nasIp;
    }

    public void setNasIp(String nasIp) {
        this.nasIp = nasIp;
    }

    public int getNasPort() {
        return this.nasPort;
    }

    public void setNasPort(int nasPort) {
        this.nasPort = nasPort;
    }

    public int getNasCoaPort() {
        return this.nasCoaPort;
    }

    public void setNasCoaPort(int nasCoaPort) {
        this.nasCoaPort = nasCoaPort;
    }

    public String getRadiusIP() {
        return this.radiusIP;
    }

    public void setRadiusIP(String radiusIP) {
        this.radiusIP = radiusIP;
    }

    public String getRadiusSecret() {
        return this.radiusSecret;
    }

    public void setRadiusSecret(String radiusSecret) {
        this.radiusSecret = radiusSecret;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public LoginConfiguration getLoginConfiguration() {
        return this.login;
    }

    public TrendooConfiguration getTrendooConfiguration() {
        return this.trendoo;
    }

    public PdfConfiguration getPdfConfiguration() {
        return this.pdf;
    }

    public PrivacyConfiguration getPrivacyConfiguration() {
        return this.privacy;
    }

    @Embeddable
    public static class LoginConfiguration implements Serializable {

        public enum LoginMode {
            USERNAME_AND_PASSWORD, EMAIL_AS_PASSWORD
        }

        public enum PasswordType {
            ALPHA, NUMERIC, ALPHANUMERIC;
        }

        @NotNull
        private LoginMode loginMode = LoginMode.USERNAME_AND_PASSWORD;

        @NotNull
        private PasswordType passwordType = PasswordType.ALPHANUMERIC;

        private int passwordLength = 8;

        @Embedded
        private LocalizedString welcome;

        @Embedded
        private LocalizedString instruction;

        @Embedded
        private LocalizedString termsShort;

        @Embedded
        private LocalizedString termsFull;

        public LoginConfiguration() {
            this.welcome = new LocalizedString("text/html");
            this.welcome.getLocalizedValues().put(Locale.ENGLISH,
                    "Welcome to our <b>Hotspot</b>!");

            this.instruction = new LocalizedString("text/html");
            this.instruction.getLocalizedValues().put(Locale.ENGLISH,
                    "Input your username and password to obtain Internet access.");

            this.termsShort = new LocalizedString("text/html");
            this.termsShort.getLocalizedValues().put(Locale.ENGLISH,
                    "I hereby declare that I have read and understood the policy "
                            + "statement as mentioned in Art. 13 D. Lgs. 196/2003 "
                            + "and I consent to the processing of my personal data "
                            + "for the purpose of Internet access as described in "
                            + "the full terms.");

            this.termsFull = new LocalizedString("text/html");
            this.termsFull.getLocalizedValues().put(Locale.ENGLISH,
                    "[Full terms]");
        }

        public LoginMode getLoginMode() {
            return this.loginMode;
        }

        public void setLoginMode(LoginMode loginMode) {
            this.loginMode = loginMode;
        }

        public PasswordType getPasswordType() {
            return this.passwordType;
        }

        public void setPasswordType(PasswordType passwordType) {
            this.passwordType = passwordType;
        }

        public int getPasswordLength() {
            return this.passwordLength;
        }

        public void setPasswordLength(int passwordLength) {
            this.passwordLength = passwordLength;
        }

        public LocalizedString getWelcomeMessage() {
            return this.welcome;
        }

        public LocalizedString getInstructionMessage() {
            return this.instruction;
        }

        public LocalizedString getTermsShortMessage() {
            return this.termsShort;
        }

        public LocalizedString getTermsFullMessage() {
            return this.termsFull;
        }

    }

    @Embeddable
    public static class PdfConfiguration implements Serializable {

        private boolean download = false;

        private String ssid;

        private String loginPage;

        private String helpNumber;

        public boolean isCanBeDownloaded() {
            return this.download;
        }

        public void setDownload(boolean canBeDownloaded) {
            this.download = canBeDownloaded;
        }

        public String getSsid() {
            return this.ssid;
        }

        public void setSsid(String ssid) {
            this.ssid = ssid;
        }

        public String getLoginPage() {
            return this.loginPage;
        }

        public void setLoginPage(String loginPage) {
            this.loginPage = loginPage;
        }

        public String getHelpNumber() {
            return this.helpNumber;
        }

        public void setHelpNumber(String helpNumber) {
            this.helpNumber = helpNumber;
        }
    }

    @Embeddable
    public static class TrendooConfiguration implements Serializable {

        private String username;

        private String password;

        private String endpoint;

        private boolean enabled = false;

        public String getUsername() {
            return this.username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return this.password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEndpoint() {
            return this.endpoint;
        }

        public void setEndpoint(String endpoint) {
            this.endpoint = endpoint;
        }

        public boolean isEnabled() {
            return this.enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

    }

    @Embeddable
    public static class PrivacyConfiguration implements Serializable {

        private String name = "[Company Name]";

        private String phoneNumber = "[Phone Number]";

        private String email = "[E-mail address]";

        private String address = "[Legal Address]";

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoneNumber() {
            return this.phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getEmail() {
            return this.email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddress() {
            return this.address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

}
