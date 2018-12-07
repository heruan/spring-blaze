package to.lova.spring.blaze.model;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Article implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private LocalizedString title = new LocalizedString();

    @Embedded
    private LocalizedString content = new LocalizedString();

    @ManyToOne
    private Person author;

    public Long getId() {
        return this.id;
    }

    public LocalizedString getTitle() {
        return this.title;
    }

    public LocalizedString getContent() {
        return this.content;
    }

    public Person getAuthor() {
        return this.author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }
}
