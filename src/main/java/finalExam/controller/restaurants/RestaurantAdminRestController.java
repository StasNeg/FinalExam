package finalExam.controller.restaurants;

import finalExam.model.restaurant.Restaurant;
import finalExam.repository.RestaurantRepository;
import finalExam.to.MenuTo;
import finalExam.util.MenuUtil;
import finalExam.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping(value = RestaurantAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantAdminRestController {
    public static final String REST_URL = "/rest/admin/restaurants";
    @Autowired
    RestaurantRepository repository;

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        repository.delete(id);
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Restaurant restaurant, @PathVariable("id") int id) {
        ValidationUtil.assureIdConsistent(restaurant, id);
        repository.save(restaurant);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@RequestBody Restaurant restaurant) {

        if (restaurant.isNew()) {
            Restaurant created = repository.save(restaurant);
            URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(REST_URL + "/{id}")
                    .buildAndExpand(created.getId()).toUri();
            return ResponseEntity.created(uriOfNewResource).body(created);
        }
        return null;
    }


}
