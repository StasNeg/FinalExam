package finalExam.repository;

import finalExam.model.users.User;
import finalExam.util.exception.NotFoundException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

/**
 * Created by Stanislav on 01.10.2017.
 */
@Repository
@Transactional(readOnly = true)
public class JPAUserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public User save(User user) {
        if (user.isNew()) {
            em.persist(user);
            return user;
        } else {
            return em.merge(user);
        }
    }

    @Override
    public User get(Integer id) {
        User getUser = em.find(User.class, id);
        if (getUser == null) throw new NotFoundException("User with id " + id + "is not available");
        return getUser;
    }

    @Override
    @Transactional
    public void delete(Integer id) {

        if (em.createNamedQuery(User.DELETE)
                .setParameter("id", id)
                .executeUpdate() == 0) throw new NotFoundException("User with id" + id + "is not available");
    }

    @Override
    public User getByEmail(String email) {
        List<User> users = em.createNamedQuery(User.BY_EMAIL, User.class)
                .setParameter(1, email)
                .getResultList();
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public List<User> getAll() {
        return em.createNamedQuery(User.ALL, User.class).getResultList();
    }

}