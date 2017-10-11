package finalExam.controller.menu;


import finalExam.model.menu.Menu;
import finalExam.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = MenuRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuRestController {
    static final String REST_URL = "/rest/restaurant";
    @Autowired
    private MenuRepository repository;

    @GetMapping("/{restaurantId}/menu/{id}")
    public Menu get(@PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) {
        return repository.get(id, restaurantId);
    }
    @GetMapping("/{restaurantId}")
    public List<Menu> getAll(@PathVariable("restaurantId") int restaurantId) {
        return repository.getAll( restaurantId);
    }


    @PostMapping("/AllBetween")
    public List<Menu> getAllBetween(@RequestParam("dateStart") LocalDate dateStart, @RequestParam("dateEnd") LocalDate dateEnd) {
        List<Menu> menu = repository.getBetweenDates(dateStart, dateEnd);
        return menu;
    }

    @PostMapping("/ByAddressBetween")
    public List<Menu> getByAddressBetween(@RequestParam("address") String address, @RequestParam("dateStart") LocalDate dateStart, @RequestParam("dateEnd") LocalDate dateEnd) {
        List<Menu> menu = repository.getByNameBetweenDates(address, dateStart, dateEnd);
        return menu;
    }

    @PostMapping("/ByAddress")
    public List<Menu> getByAddress(@RequestParam("address") String address) {
        List<Menu> menu = repository.getByNameWithMeals(address);
        return menu;
    }
}
