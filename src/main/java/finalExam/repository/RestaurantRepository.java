package finalExam.repository;

import finalExam.model.restaurant.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    List<Restaurant> getAll();

    Restaurant get(Integer id);

    void delete(Integer id);

    Restaurant save(Restaurant restaurant);
}
