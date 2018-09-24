package to.lova.spring.blaze.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Configuration implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private LoginConfiguration loginConfiguration;

    public Long getId() {
        return this.id;
    }

    public LoginConfiguration getLoginConfiguration() {
        return this.loginConfiguration;
    }

    @Embeddable
    public static class LoginConfiguration implements Serializable {

        @Embedded
        private LocalizedString messageFoo;

        @Embedded
        private LocalizedString messageBar;

        public LocalizedString getMessageFoo() {
            return this.messageFoo;
        }

        public LocalizedString getMessageBar() {
            return this.messageBar;
        }

    }

}
