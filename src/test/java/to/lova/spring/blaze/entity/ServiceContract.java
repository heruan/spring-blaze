package to.lova.spring.blaze.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class ServiceContract {

    @Id
    private String id;

    @ManyToOne
    private AbstractCustomer customer;

    @ManyToMany
    private List<ShippingAddress> addresses = new ArrayList<>();

    private LocalDate endingDate;

}
