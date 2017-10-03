package finalExam.repository;

import finalExam.model.restaurant.Restaurant;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantRepository {

    List<Restaurant> getAll();

    Restaurant get(Integer id);

    void delete(Integer id);

    Restaurant save(Restaurant restaurant);

    List<Restaurant> getByNameWithMeals(String name);

    List<Restaurant> getByNameBetweenDates(String name, LocalDate startDate, LocalDate endDate);

    List<Restaurant> getBetweenDates(LocalDate startDate, LocalDate endDate);
}
