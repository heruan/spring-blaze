package to.lova.spring.blaze.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Article implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection
    private Map<Locale, String> title = new HashMap<>();

    @ManyToOne
    private Person author;

    public Long getId() {
        return this.id;
    }

    public Map<Locale, String> getTitle() {
        return this.title;
    }

    public Person getAuthor() {
        return this.author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }
}
