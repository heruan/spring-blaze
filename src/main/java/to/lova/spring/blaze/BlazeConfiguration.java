package to.lova.spring.blaze;

import java.time.Instant;
import java.util.Date;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.blazebit.persistence.Criteria;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.integration.view.spring.EnableEntityViews;
import com.blazebit.persistence.spi.CriteriaBuilderConfiguration;
import com.blazebit.persistence.spring.data.impl.repository.BlazePersistenceRepositoryFactoryBean;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.spi.EntityViewConfiguration;

@Configuration
@EntityScan("to.lova")
@EnableEntityViews("to.lova")
@EnableJpaRepositories(basePackages = "to.lova",
        repositoryFactoryBeanClass = BlazePersistenceRepositoryFactoryBean.class)
public class BlazeConfiguration {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public CriteriaBuilderFactory createCriteriaBuilderFactory() {
        CriteriaBuilderConfiguration config = Criteria.getDefault();
        return config.createCriteriaBuilderFactory(this.entityManagerFactory);
    }

    @Bean
    public EntityViewManager createEntityViewManager(CriteriaBuilderFactory cbf,
            EntityViewConfiguration entityViewConfiguration) {
        entityViewConfiguration.registerTypeConverter(Date.class, Instant.class,
                new DateToInstantConverter());
        return entityViewConfiguration.createEntityViewManager(cbf);
    }

}
