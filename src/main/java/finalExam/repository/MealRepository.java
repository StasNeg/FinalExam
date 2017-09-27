package finalExam.repository;

import finalExam.model.meal.Meal;

import java.util.List;

/**
 * Created by Stanislav on 27.09.2017.
 */
public interface MealRepository {
    List<Meal> getAll();

    Meal get(Integer id);

    boolean delete(Integer id);

    Meal save(Meal meal);
}
