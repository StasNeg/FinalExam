package finalExam.repository;

import finalExam.model.users.User;
import finalExam.util.exception.NotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.time.LocalDate;
import java.util.List;


@Repository
@Transactional(readOnly = true)
public class JPAUserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public User save(User user) {
        if (user.isNew()) {
            em.persist(user);
            return user;
        } else {
            return em.merge(user);
        }
    }

    @Cacheable("users")
    @Override
    public User get(Integer id) {
        User getUser = em.find(User.class, id);
        if (getUser == null) throw new NotFoundException("User with id " + id + "is not available");
        return getUser;
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    @Transactional
    public void delete(Integer id) {

        if (em.createNamedQuery(User.DELETE)
                .setParameter("id", id)
                .executeUpdate() == 0) throw new NotFoundException("User with id" + id + "is not available");
    }

    @Cacheable("users")
    @Override
    public User getByEmail(String email) {
        List<User> users = em.createNamedQuery(User.BY_EMAIL, User.class)
                .setParameter(1, email)
                .getResultList();
        return DataAccessUtils.singleResult(users);
    }
    @Cacheable("users")
    @Override
    public List<User> getAll() {
        return em.createNamedQuery(User.ALL, User.class).getResultList();
    }


    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void evictCache() {
        // only for evict cache
    }

}