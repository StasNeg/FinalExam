package finalExam.controller.restaurants;

import finalExam.repository.RestaurantRepository;
import finalExam.to.RestaurantTO;
import finalExam.util.RestaurantUtil;
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
    public RestaurantTO get(@PathVariable("id") int id) {
        return RestaurantUtil.getWithSumTotal(repository.get(id));
    }
    @GetMapping
    public List<RestaurantTO> getAll() {
        return RestaurantUtil.getWithSumTotal(repository.getAll());
    }

}
