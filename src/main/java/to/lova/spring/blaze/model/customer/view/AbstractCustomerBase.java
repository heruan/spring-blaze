package to.lova.spring.blaze.model.customer.view;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;

import to.lova.spring.blaze.model.customer.entity.AbstractCustomer;

@EntityView(AbstractCustomer.class)
public interface AbstractCustomerBase {

    @IdMapping
    Long getId();

    String getName();

    String getEmailAddress();

    String getTelephoneNumber();

    String getFaxNumber();

    String getNumber();

    String getStreet();

    String getDistrict();

    String getPostalCode();

    String getCity();

    String getProvince();

    String getRegion();

    String getCountry();

    default String getFullAddress() {
        return Stream
                .of(this.getNumber(), this.getStreet(), this.getDistrict(),
                        this.getPostalCode(), this.getCity(),
                        this.getProvince(), this.getRegion(), this.getCountry())
                .filter(Objects::nonNull).collect(Collectors.joining(", "));
    }

}
