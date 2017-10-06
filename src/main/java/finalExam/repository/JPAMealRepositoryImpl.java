package finalExam.repository;

import finalExam.model.meal.Meal;
import finalExam.model.restaurant.Restaurant;
import finalExam.util.exception.NotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
@Transactional(readOnly = true)
public class JPAMealRepositoryImpl implements MealRepository {


    @PersistenceContext
    private EntityManager em;


    public JPAMealRepositoryImpl() {
    }

    @Override
    public List<Meal> getAll(Integer restaurantId) {
        return em.createNamedQuery(Meal.ALL, Meal.class)
                .setParameter("restaurantId", restaurantId)
                .getResultList();
    }

    @Override
    public Meal get(Integer id, Integer restaurantId) {
        Meal meal = em.find(Meal.class, id);
        if(meal == null || !meal.getRestaurant().getId().equals(restaurantId))
            throw new NotFoundException("Meal with id " + id + " and with restaurant Id "
                    + restaurantId + " is not available");
        return meal;
    }

    @Transactional
    @Override
    public void delete(Integer id, Integer restaurantId) {
        if (em.createNamedQuery(Meal.DELETE)
                .setParameter("id", id)
                .setParameter("restaurantId", restaurantId)
                .executeUpdate() == 0) throw new NotFoundException("Meal with id " + id + " and with restaurant Id "
                + restaurantId + " is not available ");
    }

    @Override
    @Transactional
    public Meal save(Meal meal, Integer restaurantId) {
        if (!meal.isNew() && get(meal.getId(), restaurantId) == null) {
            throw new NotFoundException("Can't create/update " + meal + " for restaurant id " + restaurantId);
        }
        meal.setRestaurant(em.getReference(Restaurant.class, restaurantId));
        if (meal.isNew()) {
            em.persist(meal);
            return meal;
        } else {
            return em.merge(meal);
        }
    }
}
