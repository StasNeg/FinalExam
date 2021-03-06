package finalExam.repository.jpaImp;

import finalExam.model.user.User;
import finalExam.repository.AbstractDaoImpl;
import finalExam.repository.UserRepository;
import finalExam.util.exception.NotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;


@Repository
@Transactional(readOnly = true)
public class JPAUserRepositoryImpl extends AbstractDaoImpl<User> implements UserRepository {

    public JPAUserRepositoryImpl() {
        super(User.class);
    }

    //    @PersistenceContext
//    private EntityManager em;

    @Override
    @CacheEvict(value = "user", allEntries = true)
    @Transactional
    public User save(User user) {
        if (user.isNew()) {
            em.persist(user);
            return user;
        } else {
            return em.merge(user);
        }
    }

    @Cacheable("user")
    @Override
    public User get(Integer id) {
        User getUser = em.find(User.class, id);
        if (getUser == null) throw new NotFoundException("User with id " + id + "is not available");
        return getUser;
    }

    @CacheEvict(value = "user", allEntries = true)
    @Override
    @Transactional
    public void delete(Integer id) {

        if (em.createNamedQuery(User.DELETE)
                .setParameter("id", id)
                .executeUpdate() == 0) throw new NotFoundException("User with id" + id + "is not available");
    }

    @Cacheable("user")
    @Override
    public User getByEmail(String email) {
        List<User> users = em.createNamedQuery(User.BY_EMAIL, User.class)
                .setParameter(1, email)
                .getResultList();
        return DataAccessUtils.singleResult(users);
    }
    @Cacheable("user")
    @Override
    public List<User> getAll() {
        return em.createNamedQuery(User.ALL, User.class).getResultList();
    }


    @CacheEvict(value = "user", allEntries = true)
    @Override
    public void evictCache() {
        // only for evict cache
    }

}