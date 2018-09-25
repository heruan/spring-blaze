package to.lova.spring.blaze.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blazebit.persistence.spring.data.repository.EntityViewSpecificationExecutor;

import to.lova.spring.blaze.entity.Configuration;
import to.lova.spring.blaze.entity.HotspotConfiguration;
import to.lova.spring.blaze.view.HotspotConfigurationView;

public interface ConfigurationRepository
        extends JpaRepository<Configuration, Long>,
        EntityViewSpecificationExecutor<HotspotConfigurationView, HotspotConfiguration> {

}
