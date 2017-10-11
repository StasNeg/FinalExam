package finalExam.repository;

import finalExam.model.restaurant.Restaurant;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantRepository {

    Restaurant get(Integer id);

    void delete(Integer id);

    Restaurant save(Restaurant restaurant);

    List<Restaurant> getAll();

}
