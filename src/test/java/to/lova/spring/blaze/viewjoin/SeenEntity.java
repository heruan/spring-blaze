package to.lova.spring.blaze.viewjoin;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class SeenEntity<T extends Serializable, U extends Serializable>
        implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private T entity;

    @ManyToOne
    private U observer;

    public Long getId() {
        return this.id;
    }

    public T getEntity() {
        return this.entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public U getObserver() {
        return this.observer;
    }

    public void setObserver(U observer) {
        this.observer = observer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.entity, this.observer);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public boolean equals(Object obj) {
        if (obj instanceof SeenEntity) {
            SeenEntity other = (SeenEntity) obj;
            return Objects.equals(this.entity, other.entity)
                    && Objects.equals(this.observer, other.observer);
        } else {
            return false;
        }
    }

}
