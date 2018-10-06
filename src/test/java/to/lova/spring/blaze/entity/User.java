package to.lova.spring.blaze.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection
    @MapKeyColumn(unique = true)
    private Map<String, Boolean> emailAddresses = new HashMap<>();

    public Long getId() {
        return this.id;
    }

    public Map<String, Boolean> getEmailAddresses() {
        return this.emailAddresses;
    }
}
