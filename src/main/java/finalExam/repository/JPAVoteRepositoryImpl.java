package finalExam.repository;

import finalExam.model.restaurant.Restaurant;
import finalExam.model.users.User;
import finalExam.model.votes.Vote;
import finalExam.util.exception.NotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JPAVoteRepositoryImpl implements VoteRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Vote get(Integer id) {
        Vote vote = em.find(Vote.class, id);
        return vote;
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        if (em.createNamedQuery(Vote.DELETE)
                .setParameter("id", id)
                .executeUpdate() == 0) throw new NotFoundException("Vote with id" + id + "is not available");
    }

    @Override
    @Transactional
    public Vote save(Vote vote) {
        if (vote.isNew()) {
            em.persist(vote);
            return vote;
        } else {
            return em.merge(vote);
        }
    }

    @Override
    public List<Vote> getAll() {
        return em.createNamedQuery(Vote.ALL, Vote.class).getResultList();
    }

    @Override
    public List<User> getAllUserByDay(LocalDate date) {
        return em.createNamedQuery(Vote.GET_ALL_USERS_BY_DATE, User.class).setParameter("date", date).getResultList();
    }

    @Override
    public List<Restaurant> getAllRestaurantByDay(LocalDate date) {
        return em.createNamedQuery(Vote.GET_ALL_RESTAURANT_BY_DATE, Restaurant.class).setParameter("date", date).getResultList();
    }
}
