package finalExam.repository;

import finalExam.model.restaurant.Restaurant;
import finalExam.util.exception.NotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JPARestaurantRepositoryImpl implements RestaurantRepository {

    @PersistenceContext
    private EntityManager em;

    @Cacheable("restaurants")
    @Override
    public List<Restaurant> getAll() {
        return em.createNamedQuery(Restaurant.ALL, Restaurant.class).getResultList();
    }

    @Override
    public Restaurant get(Integer id) {
        Restaurant getRestaunat = em.find(Restaurant.class, id);
        if (getRestaunat == null) throw new NotFoundException("Restaurant with id " + id + " is not available");
        return getRestaunat;
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Transactional
    @Override
    public void delete(Integer id) {
        if (em.createNamedQuery(Restaurant.DELETE)
                .setParameter("id", id)
                .executeUpdate() == 0) throw new NotFoundException("Restaurant id " + id + " is not available");

    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Transactional
    @Override
    public Restaurant save(Restaurant restaurant) {
        if (restaurant.isNew()) {
            em.persist(restaurant);
            return restaurant;
        } else {
            return em.merge(restaurant);
        }
    }

    @Cacheable("restaurants")
    @Override
    public List<Restaurant> getByNameWithMeals(String name) {
        return em.createNamedQuery(Restaurant.ALL_BY_ADDRESS, Restaurant.class)
                .setParameter(1,name)
                .getResultList();
    }

    @Cacheable("restaurants")
    @Override
    public List<Restaurant> getByNameBetweenDates(String name, LocalDate startDate, LocalDate endDate) {
        return em.createNamedQuery(Restaurant.GET_BY_ADDRESS_BETWEEN_DATES, Restaurant.class)
                .setParameter(1,name)
                .setParameter(2, startDate)
                .setParameter(3, endDate).getResultList();
    }

    @Cacheable("restaurants")
    @Override
    public List<Restaurant> getBetweenDates(LocalDate startDate, LocalDate endDate) {
        return em.createNamedQuery(Restaurant.GET_BETWEEN_DATES, Restaurant.class)
                .setParameter(1, startDate)
                .setParameter(2, endDate).getResultList();
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void evictCache() {
        // only for evict cache
    }
}
