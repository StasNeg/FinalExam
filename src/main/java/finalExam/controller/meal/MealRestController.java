package finalExam.controller.meal;

import finalExam.model.meal.Meal;
import finalExam.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @DeleteMapping("/admin/{restaurantId}/{id}")
    public void delete(@PathVariable("id") int id,@PathVariable("restaurantId") int restaurantId) {
        repository.delete(id, restaurantId);
    }


    @GetMapping(value = "/{restaurantId}")
    public List<Meal> getAll(@PathVariable("restaurantId") int restaurantId) {
        List<Meal> meals = repository.getAll(restaurantId);
        return meals;
    }


    @PutMapping(value = "/admin/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Meal meal,@PathVariable("restaurantId") int restaurantId) {
        repository.save(meal, restaurantId);
    }

    @PostMapping(value = "/admin/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@RequestBody Meal meal, @PathVariable("restaurantId") int restaurantId) {
        Meal created = repository.save(meal, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}