package to.lova.spring.blaze.view;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;

import to.lova.spring.blaze.model.LocalizedEntity;
import to.lova.spring.blaze.model.LocalizedEntity_;

@EntityView(LocalizedEntity.class)
public interface LocalizedEntityId<T extends LocalizedEntity>
        extends Specification<T> {

    @IdMapping
    Long getId();

    @Override
    default Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
            CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get(LocalizedEntity_.id),
                this.getId());
    }

}
