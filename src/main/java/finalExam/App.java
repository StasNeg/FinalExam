package finalExam;

import finalExam.model.meal.Meal;
import finalExam.repository.Meal.InMemoryMealRepository;
import finalExam.repository.MealRepository;

import java.util.List;


public class App 
{
    public static void main( String[] args )
    {
        MealRepository repository = new InMemoryMealRepository();

        Meal mealNew = new Meal("New", 48);
        List<Meal> meals = repository.getAll();
        System.out.println(meals);
        System.out.println(repository.get(meals.get(0).getId()));

    }
}
