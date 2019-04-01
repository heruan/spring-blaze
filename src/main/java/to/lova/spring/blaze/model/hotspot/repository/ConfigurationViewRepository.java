package to.lova.spring.blaze.model.hotspot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import to.lova.spring.blaze.model.hotspot.view.HotspotConfigurationView;

public interface ConfigurationViewRepository
        extends JpaRepository<HotspotConfigurationView, Long> {

}
