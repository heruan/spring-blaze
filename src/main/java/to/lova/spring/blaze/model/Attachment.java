package to.lova.spring.blaze.model;

import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class Attachment {

    String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Attachment)) {
            return false;
        }
        Attachment other = (Attachment) obj;
        return Objects.equals(this.name, other.name);
    }

}
