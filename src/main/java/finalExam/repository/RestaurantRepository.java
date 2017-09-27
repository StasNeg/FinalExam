package finalExam.repository;

import finalExam.model.restaurant.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    List<Restaurant> getAll(Integer userId);

    Restaurant get(Integer id, Integer userId);

    boolean delete(Integer id, Integer userId);

    Restaurant save(Restaurant restaurant, Integer userId);
}
