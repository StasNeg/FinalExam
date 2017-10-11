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
    static final String REST_URL = "/rest/menu";

    @Autowired
    MealRepository repository;

    @GetMapping("/{menuId}/meals/{id}")
    public Meal get(@PathVariable("id") int id, @PathVariable("menuId") int menuId) {
        return repository.get(id, menuId);
    }

    @GetMapping(value = "/{menuId}")
    public List<Meal> getAll(@PathVariable("menuId") int menuId) {
        List<Meal> meals = repository.getAll(menuId);
        return meals;
    }

}