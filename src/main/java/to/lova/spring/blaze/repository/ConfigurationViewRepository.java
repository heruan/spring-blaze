package to.lova.spring.blaze.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import to.lova.spring.blaze.view.HotspotConfigurationView;

public interface ConfigurationViewRepository
        extends JpaRepository<HotspotConfigurationView, Long> {

}
