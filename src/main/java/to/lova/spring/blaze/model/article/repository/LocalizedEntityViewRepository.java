package to.lova.spring.blaze.model.article.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import to.lova.spring.blaze.model.OffsetBasedEntityViewSpecificationExecutor;
import to.lova.spring.blaze.model.article.entity.LocalizedEntity;
import to.lova.spring.blaze.model.article.view.LocalizedEntityView;

@NoRepositoryBean
public interface LocalizedEntityViewRepository<T extends LocalizedEntityView<E>, E extends LocalizedEntity>
        extends JpaRepository<T, Long>,
        OffsetBasedEntityViewSpecificationExecutor<T, E> {

    Optional<T> findByName(String name);

}
