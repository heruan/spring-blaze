package to.lova.spring.blaze.model.common.view;

import java.time.Instant;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;

import to.lova.spring.blaze.model.common.entity.EntityRevision;

@EntityView(EntityRevision.class)
public interface EntityRevisionDetail {

    @IdMapping
    Long getId();

    Instant getTimestamp();

}