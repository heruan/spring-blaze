package to.lova.spring.blaze.model.article.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import to.lova.spring.blaze.model.article.entity.LocalizedEntity;
import to.lova.spring.blaze.model.article.view.LocalizedEntityListItem;
import to.lova.spring.blaze.model.common.repository.OffsetBasedEntityViewSpecificationExecutor;

@NoRepositoryBean
public interface LocalizedEntityListItemRepository<V extends LocalizedEntityListItem<E>, E extends LocalizedEntity, I extends Serializable>
        extends JpaRepository<V, I>,
        OffsetBasedEntityViewSpecificationExecutor<V, E> {

}
