package finalExam.repository;

import finalExam.model.meal.Meal;

import java.util.List;

/**
 * Created by Stanislav on 27.09.2017.
 */
public interface MealRepository {
    List<Meal> getAll(Integer restaurantId);

    Meal get(Integer id,Integer restaurantId);

    void delete(Integer id, Integer restaurantId);

    Meal save(Meal meal, Integer restaurantId);
}
