package finalExam.controller.restaurants;

import finalExam.repository.RestaurantRepository;
import finalExam.to.RestaurantWithPriceTotal;
import finalExam.util.RestaurantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {
    static final String REST_URL = "/rest/restaurants";

    @Autowired
    RestaurantRepository repository;

    @GetMapping("/{id}")
    public RestaurantWithPriceTotal get(@PathVariable("id") int id) {
        return RestaurantUtil.getWithSumTotal(Arrays.asList(repository.get(id))).get(0);
    }

    @DeleteMapping("/admin/{id}")
    public void delete(@PathVariable("id") int id) {
        repository.delete(id);
    }

    @GetMapping
    public List<RestaurantWithPriceTotal> getAll() {
        return RestaurantUtil.getWithSumTotal(repository.getAll());
    }

    @PutMapping(value = "/admin", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody RestaurantWithPriceTotal restaurant) {
        repository.save(RestaurantUtil.getFromSumTotal(restaurant));
    }

    @PostMapping(value = "/admin", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantWithPriceTotal> createWithLocation(@RequestBody RestaurantWithPriceTotal restaurant) {
        RestaurantWithPriceTotal created = RestaurantUtil
                .getWithSumTotal(Arrays.asList(repository.save(RestaurantUtil.getFromSumTotal(restaurant))))
                .get(0);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
