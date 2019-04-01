package to.lova.spring.blaze.model.common.view;

import java.time.Instant;
import java.util.Optional;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.Mapping;

import to.lova.spring.blaze.model.common.entity.User;

@EntityView(User.class)
public interface UserDetail extends UserBase {

    @Mapping("sum(case when (sessions.destroyed = false) then 1 else 0 end)")
    long getActiveSessionCount();

    @Mapping("max(sessions.heartbeatInstant)")
    Optional<Instant> getLastActiveInstant();

}
