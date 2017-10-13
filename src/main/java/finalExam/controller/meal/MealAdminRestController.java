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
    static final String REST_URL = "/rest/admin/menu";
    @Autowired
    private MealRepository repository;

    @DeleteMapping("/{menuId}/meals/{id}")
    public void delete(@PathVariable("id") int id, @PathVariable("menuId") int menuId) {
        repository.delete(id, menuId);
    }

    @PutMapping(value = "/{menuId}/meals/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Meal update(@RequestBody Meal meal, @PathVariable("menuId") int menuId, @PathVariable("id") int id) {
            ValidationUtil.assureIdConsistent(meal, id);
            return repository.save(meal, menuId);
    }

    @PostMapping(value = "/{menuId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@RequestBody Meal meal, @PathVariable("menuId") int menuId) {
        if (meal.isNew()) {
            Meal created = repository.save(meal, menuId);
            URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(REST_URL +menuId+"/meals"+ "/{menuId}")
                    .buildAndExpand(created.getId()).toUri();
            return ResponseEntity.created(uriOfNewResource).body(created);
        }
        return null;
    }
}
