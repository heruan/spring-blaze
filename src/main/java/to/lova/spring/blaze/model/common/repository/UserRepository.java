package to.lova.spring.blaze.model.common.repository;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import to.lova.spring.blaze.model.common.entity.User;
import to.lova.spring.blaze.model.common.entity.User_;
import to.lova.spring.blaze.model.common.view.UserDetail;

public interface UserRepository
        extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    default Optional<User> findByEmailAddress(String... emailAddresses) {
        return this.findOne((root, query, builder) -> {
            var subquery = query.subquery(Boolean.class);
            var join = subquery.correlate(root).join(User_.emailAddresses);
            var addresses = Stream.of(emailAddresses).map(String::toLowerCase)
                .collect(Collectors.toSet());
            var confirmed = join.value();
            var address = join.key();
            var p1 = builder.isTrue(confirmed);
            var p2 = builder.lower(address).in(addresses);
            return builder.exists(subquery.where(p1, p2));
        });
    }

    UserDetail findByName(String name);

}
