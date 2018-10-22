package to.lova.spring.blaze;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import com.blazebit.persistence.view.EntityViewManager;

import to.lova.spring.blaze.model.User;
import to.lova.spring.blaze.repository.GroupRepository;
import to.lova.spring.blaze.repository.RoleRepository;
import to.lova.spring.blaze.repository.UserRepository;
import to.lova.spring.blaze.view.GroupDetail;
import to.lova.spring.blaze.view.RoleDetail;

@DataJpaTest
@ContextConfiguration(classes = BlazeConfiguration.class)
public class UpdatableCollectionTests {

    @Autowired
    private TestEntityManager em;

    @BeforeEach
    public void init() {
        var user1 = new User();
        user1.setName("one");
        this.em.persist(user1);

        var user2 = new User();
        user2.setName("two");
        this.em.persist(user2);

        var user3 = new User();
        user3.setName("three");
        this.em.persist(user3);

        this.em.flush();
        this.em.clear();
    }

    @Test
    public void testMapCollectionSubquery(
            @Autowired UserRepository repository) {
        repository.findByEmailAddress("foo@bar.baz");
    }

    @Test
    public void testAddSingle(@Autowired EntityViewManager evm,
            @Autowired UserRepository userRepository,
            @Autowired GroupRepository groupRepository) {
        var em = this.em.getEntityManager();
        var user = userRepository.findByName("one");
        var group = evm.create(GroupDetail.class);
        group.getUsers().add(user);
        evm.update(em, group);
        em.flush();
        em.clear();

        var size = groupRepository.findById(group.getId()).get().getUsers()
                .size();
        assertEquals(1, size);

        group = evm.find(em, GroupDetail.class, group.getId());
        group.getUsers().remove(user);
        evm.update(em, group);
        em.flush();
        em.clear();

        size = groupRepository.findById(group.getId()).get().getUsers().size();
        assertEquals(0, size);
    }

    @Test
    public void testAddMultiple(@Autowired EntityViewManager evm,
            @Autowired UserRepository userRepository,
            @Autowired GroupRepository groupRepository) {
        var em = this.em.getEntityManager();
        var user1 = userRepository.findByName("one");
        var user2 = userRepository.findByName("two");
        var group = evm.create(GroupDetail.class);
        group.getUsers().addAll(Set.of(user1, user2));
        evm.update(em, group);
        em.flush();
        em.clear();

        var size = groupRepository.findById(group.getId()).get().getUsers()
                .size();
        assertEquals(2, size);

        group = evm.find(em, GroupDetail.class, group.getId());
        group.getUsers().retainAll(Set.of());
        evm.update(em, group);
        em.flush();
        em.clear();

        size = groupRepository.findById(group.getId()).get().getUsers().size();
        assertEquals(0, size);
    }

    @Test
    public void testInverseAddSingle(@Autowired EntityViewManager evm,
            @Autowired UserRepository userRepository,
            @Autowired RoleRepository roleRepository) {
        var em = this.em.getEntityManager();
        var user = userRepository.findByName("one");
        var role = evm.create(RoleDetail.class);
        role.getUsers().add(user);
        evm.update(em, role);
        em.flush();
        em.clear();

        var size = roleRepository.findById(role.getId()).get().getUsers()
                .size();
        assertEquals(1, size);

        role = evm.find(em, RoleDetail.class, role.getId());
        role.getUsers().remove(user);
        evm.update(em, role);
        em.flush();
        em.clear();

        size = roleRepository.findById(role.getId()).get().getUsers().size();
        assertEquals(0, size);
    }

    @Test
    public void testInverseAddMultiple(@Autowired EntityViewManager evm,
            @Autowired UserRepository userRepository,
            @Autowired RoleRepository roleRepository) {
        var em = this.em.getEntityManager();
        var user1 = userRepository.findByName("one");
        var user2 = userRepository.findByName("two");
        var role = evm.create(RoleDetail.class);
        role.getUsers().addAll(Set.of(user1, user2));
        evm.update(em, role);
        em.flush();
        em.clear();

        var size = roleRepository.findById(role.getId()).get().getUsers()
                .size();
        assertEquals(2, size);

        role = evm.find(em, RoleDetail.class, role.getId());
        role.getUsers().retainAll(Set.of());
        evm.update(em, role);
        em.flush();
        em.clear();

        size = roleRepository.findById(role.getId()).get().getUsers().size();
        assertEquals(0, size);
    }

}
