package to.lova.spring.blaze.viewjoin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import to.lova.spring.blaze.collection.model.User;
import to.lova.spring.blaze.misc.model.AbstractCustomer;

@Entity
public class Ticket {

    @GeneratedValue
    @Id
    Long id;

    @ManyToOne
    AbstractCustomer customer;

    @ManyToOne
    User author;

    @ManyToOne
    User assignee;

    boolean active;

}
