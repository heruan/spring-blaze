package to.lova.spring.blaze.model.common.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.envers.Audited;

@Entity
@Audited
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(mappedBy = User_.ROLES)
    private Set<User> users = new HashSet<>();

    public Set<User> getUsers() {
        return this.users;
    }

}
