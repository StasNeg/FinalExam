package finalExam.controller.restaurants;

import finalExam.model.restaurant.Restaurant;
import finalExam.repository.RestaurantRepository;
import finalExam.to.MenuTo;
import finalExam.util.MenuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {
    public static final String REST_URL = "/rest/restaurants";

    @Autowired
    RestaurantRepository repository;

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable("id") int id) {
        return repository.get(id);
    }

    @GetMapping()
    public List<Restaurant> getAll() {
        return repository.getAll();
    }


}
