package to.lova.spring.blaze.collection.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;

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

}
