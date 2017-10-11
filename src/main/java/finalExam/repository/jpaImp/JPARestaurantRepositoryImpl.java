package finalExam.repository.jpaImp;

import finalExam.model.restaurant.Restaurant;
import finalExam.repository.AbstractDaoImpl;
import finalExam.repository.RestaurantRepository;
import finalExam.util.exception.NotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional(readOnly = true)
public class JPARestaurantRepositoryImpl extends AbstractDaoImpl<Restaurant> implements RestaurantRepository {

    public JPARestaurantRepositoryImpl() {
        super(Restaurant.class);
    }


    @Override
    public Restaurant get(Integer id) {
        Restaurant getRestaurant = super.get(id);
        if (getRestaurant == null) throw new NotFoundException("Restaurant with id " + id + " is not available");
        return getRestaurant;
    }


    @Transactional
    @Override
    public void delete(Integer id) {
        if (em.createNamedQuery(Restaurant.DELETE)
                .setParameter("id", id)
                .executeUpdate() == 0) throw new NotFoundException("Restaurant id " + id + " is not available");

    }


    @Transactional
    @Override
    public Restaurant save(Restaurant restaurant) {
        if (restaurant.isNew()) {
            super.create(restaurant);
            return restaurant;
        } else {
            return super.update(restaurant);
        }
    }

    @Override
    public List<Restaurant> getAll() {
        return em.createNamedQuery(Restaurant.ALL, Restaurant.class).getResultList();
    }
}
