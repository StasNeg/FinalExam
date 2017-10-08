package finalExam.controller.meal;

import finalExam.model.meal.Meal;
import finalExam.repository.MealRepository;
import finalExam.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = MealAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealAdminRestController {
    static final String REST_URL = "/rest/admin/meals";
    @Autowired
    private MealRepository repository;

    @DeleteMapping("/{restaurantId}/{id}")
    public void delete(@PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) {
        repository.delete(id, restaurantId);
    }

    @PutMapping(value = "/{restaurantId}/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Meal meal, @PathVariable("restaurantId") int restaurantId, @PathVariable("id") int id) {
            ValidationUtil.assureIdConsistent(meal, id);
            repository.save(meal, restaurantId);
    }

    @PostMapping(value = "/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@RequestBody Meal meal, @PathVariable("restaurantId") int restaurantId) {
        if (meal.isNew()) {
            Meal created = repository.save(meal, restaurantId);
            URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(REST_URL + "/{id}")
                    .buildAndExpand(created.getId()).toUri();
            return ResponseEntity.created(uriOfNewResource).body(created);
        }
        return null;
    }
}
