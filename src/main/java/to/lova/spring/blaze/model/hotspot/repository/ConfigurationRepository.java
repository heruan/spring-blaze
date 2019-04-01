package to.lova.spring.blaze.model.hotspot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blazebit.persistence.spring.data.repository.EntityViewSpecificationExecutor;

import to.lova.spring.blaze.model.hotspot.entity.HotspotConfiguration;
import to.lova.spring.blaze.model.hotspot.view.HotspotConfigurationView;

public interface ConfigurationRepository
        extends JpaRepository<HotspotConfiguration, Long>,
        EntityViewSpecificationExecutor<HotspotConfigurationView, HotspotConfiguration> {

}
