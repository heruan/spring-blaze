package to.lova.spring.blaze.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blazebit.persistence.view.EntityViewManager;

import to.lova.spring.blaze.model.customer.repository.CustomerDetailRepository;
import to.lova.spring.blaze.model.customer.view.AbstractCustomerDetail;
import to.lova.spring.blaze.model.customer.view.CustomerDetail;

@RestController
@RequestMapping("/customers")
public class CustomerEndpoint
        implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private EntityViewManager evm;

    @Autowired
    private CustomerDetailRepository repository;

    @GetMapping("/{name}")
    public AbstractCustomerDetail getCustomer(
            @PathVariable("name") String name) {
        return this.repository.findByName(name).orElseThrow();
    }

    @PatchMapping("/{name}/hours/{hours}")
    public AbstractCustomerDetail patchCustomer(
            @PathVariable("name") String name,
            @PathVariable("hours") String hours) {
        var customer = this.repository.findByName(name).orElseThrow();
        customer.getServiceDetail().setServiceHours(hours);
        return this.repository.save(customer);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        var customer = this.evm.create(CustomerDetail.class);
        customer.setName("foo");
        this.repository.save(customer);
    }

}
