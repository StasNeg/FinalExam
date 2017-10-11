package finalExam.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class AbstractDaoImpl<T> implements AbstractDao<T, Integer> {

    private Class<T> type;

    public AbstractDaoImpl(Class<T> type) {
        this.type = type;
    }

    @PersistenceContext
    protected EntityManager em;

    @Override
    public T create(T created) {
        em.persist(created);
        return created;
    }

    @Override
    public T update(T edit) {
        return em.merge(edit);
    }

    @Override
    public void delete(Integer id) {
        em.remove(get(id));
    }


    @Override
    public T get(Integer id) {
        return em.find(type, id);
    }
}
