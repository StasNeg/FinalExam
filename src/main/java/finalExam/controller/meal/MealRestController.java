package finalExam.controller.meal;

import finalExam.model.meal.Meal;
import finalExam.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = MealRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealRestController {
    static final String REST_URL = "/rest/meals";

    @Autowired
    MealRepository repository;

    @GetMapping("/{restaurantId}/{id}")
    public Meal get(@PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) {
        return repository.get(id, restaurantId);
    }

    @GetMapping(value = "/{restaurantId}")
    public List<Meal> getAll(@PathVariable("restaurantId") int restaurantId) {
        List<Meal> meals = repository.getAll(restaurantId);
        return meals;
    }

}