package finalExam.repository;

import finalExam.AuthorizedUser;
import finalExam.model.restaurant.Restaurant;
import finalExam.model.users.User;
import finalExam.model.votes.Vote;
import finalExam.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

import static finalExam.util.UserUtil.fromTo;
import static finalExam.util.ValidationUtil.TIME_VOTE_LIMIT;
import static finalExam.util.ValidationUtil.onTimeVote;
import static java.time.LocalDate.now;

@Repository
@Transactional(readOnly = true)
public class JPAVoteRepositoryImpl implements VoteRepository{

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private RestaurantRepository restaurantRepository;


    @Override
    public List<Vote> getAll() {
        return em.createNamedQuery(Vote.ALL, Vote.class).getResultList();
    }

    private Vote getByUserAndDate(Integer userId, LocalDate voteDate) {
        List<Vote> votes = em.createNamedQuery(Vote.GET_BY_USER_AND_DATE, Vote.class)
                .setParameter("userId", userId)
                .setParameter("voteDate", voteDate)
                .getResultList();
        return votes.size() > 0 ? votes.get(0) : null;
    }

    @Transactional
    @Override
    public Vote save(Restaurant restaurant, User user) {
        restaurant = restaurantRepository.get(restaurant.getId());
        Vote save = getByUserAndDate(user.getId(), now());
        if (save == null) {
            save = new Vote(null, user, restaurant, now());
        } else {
            save.setRestaurant(restaurant);
        }
        return save(save);
    }
    @Transactional
    @Override
    public Vote save(Integer restaurantId, User user) {
        Restaurant restaurant = restaurantRepository.get(restaurantId);
        Vote save = getByUserAndDate(user.getId(), now());
        if (save == null) {
            save = new Vote(null, user, restaurant, now());
        } else {
            save.setRestaurant(restaurant);
        }
        return save(save);
    }


    private Vote save(Vote save) {
        if (save.isNew()) {
            em.persist(save);
            return save;
        } else {
            if (onTimeVote()) {
                return em.merge(save);
            }
            throw new NotFoundException("You can't voting again, after" + TIME_VOTE_LIMIT + "'Clock");
        }
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
