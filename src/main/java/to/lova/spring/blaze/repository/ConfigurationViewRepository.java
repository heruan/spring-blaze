package to.lova.spring.blaze.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import to.lova.spring.blaze.view.ConfigurationView;

public interface ConfigurationViewRepository
        extends JpaRepository<ConfigurationView, Long> {

}
