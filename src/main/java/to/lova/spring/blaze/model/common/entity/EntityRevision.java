package to.lova.spring.blaze.model.common.entity;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.envers.ModifiedEntityNames;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

@Entity
@RevisionEntity
public class EntityRevision
        implements Comparable<EntityRevision>, AuditMetadata, Serializable {

    @Id
    @RevisionNumber
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @RevisionTimestamp
    private Date timestamp;

    @ElementCollection
    @ModifiedEntityNames
    private Set<String> modifiedEntityNames = new HashSet<>();

    Date getInstant() {
        return this.timestamp;
    }

    @Override
    public Optional<Long> getRevisionNumber() {
        return Optional.of(this.id);
    }

    @Override
    public Optional<Instant> getRevisionInstant() {
        return Optional.of(this.timestamp.toInstant());
    }

    @Override
    @Deprecated
    public Optional<LocalDateTime> getRevisionDate() {
        return this.getRevisionInstant()
                .map(i -> LocalDateTime.ofInstant(i, ZoneOffset.UTC));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getDelegate() {
        return (T) this;
    }

    @Override
    public int compareTo(EntityRevision o) {
        return Long.compare(this.id, o.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EntityRevision) {
            EntityRevision other = (EntityRevision) obj;
            return Objects.equals(this.id, other.id);
        }
        return false;
    }

}