package to.lova.spring.blaze.collection.model;

import java.time.Instant;
import java.util.Optional;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.Mapping;

@EntityView(User.class)
public interface UserDetail {

    @IdMapping
    Long getId();

    @Mapping("sum(case when (sessions.destroyed = false) then 1 else 0 end)")
    long getActiveSessionCount();

    @Mapping("max(sessions.heartbeatInstant)")
    Optional<Instant> getLastActiveInstant();

}
