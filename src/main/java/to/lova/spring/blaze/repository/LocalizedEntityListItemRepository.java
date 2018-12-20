package to.lova.spring.blaze.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import to.lova.spring.blaze.model.LocalizedEntity;
import to.lova.spring.blaze.view.LocalizedEntityListItem;

@NoRepositoryBean
public interface LocalizedEntityListItemRepository<V extends LocalizedEntityListItem<E>, E extends LocalizedEntity, I extends Serializable>
        extends JpaRepository<V, I>,
        OffsetBasedEntityViewSpecificationExecutor<V, E> {

}
