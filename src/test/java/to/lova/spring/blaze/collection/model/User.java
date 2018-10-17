package to.lova.spring.blaze.collection.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;

import to.lova.spring.blaze.misc.model.UserSession;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ElementCollection
    @MapKeyColumn(unique = true)
    private Map<String, Boolean> emailAddresses = new HashMap<>();

    @ManyToMany
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(mappedBy = "users")
    private Set<Group> groups = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private List<UserSession> sessions = new ArrayList<>();

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Boolean> getEmailAddresses() {
        return this.emailAddresses;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public Set<Group> getGroups() {
        return this.groups;
    }

    public List<UserSession> getSessions() {
        return this.sessions;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User other = (User) obj;
        return Objects.equals(this.id, other.id);
    }

}
