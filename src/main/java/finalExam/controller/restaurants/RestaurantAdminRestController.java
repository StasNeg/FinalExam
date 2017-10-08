package finalExam.controller.restaurants;

import finalExam.model.restaurant.Restaurant;
import finalExam.repository.RestaurantRepository;
import finalExam.to.RestaurantTO;
import finalExam.util.RestaurantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;

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


    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody RestaurantTO restaurant) {
        Restaurant update = RestaurantUtil.getFromSumTotal(restaurant);
        if (!update.isNew())
            repository.save(RestaurantUtil.getFromSumTotal(restaurant));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantTO> createWithLocation(@RequestBody RestaurantTO restaurant) {
        Restaurant newRestaurant = RestaurantUtil.getFromSumTotal(restaurant);
        if (newRestaurant.isNew()) {
            RestaurantTO created = RestaurantUtil
                    .getWithSumTotal(Arrays.asList(repository.save(newRestaurant)))
                    .get(0);
            URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(REST_URL + "/{id}")
                    .buildAndExpand(created.getId()).toUri();
            return ResponseEntity.created(uriOfNewResource).body(created);
        }
        return null;
    }


}
