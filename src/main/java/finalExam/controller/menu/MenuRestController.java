package finalExam.controller.menu;


import finalExam.model.menu.Menu;
import finalExam.repository.MenuRepository;
import finalExam.to.MenuTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static finalExam.util.MenuUtil.getWithSumTotal;

@RestController
@RequestMapping(value = MenuRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuRestController {
    static final String REST_URL = "/rest/restaurant";
    @Autowired
    private MenuRepository repository;

    @GetMapping("/{restaurantId}/menu/{id}")
    public MenuTo get(@PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) {
        return getWithSumTotal(repository.get(id, restaurantId));
    }
    @GetMapping("/{restaurantId}")
    public List<MenuTo> getAll(@PathVariable("restaurantId") int restaurantId) {
        return getWithSumTotal(repository.getAll( restaurantId));
    }


    @GetMapping(value = "/AllBetween")
    public List<MenuTo> getAllBetween(@RequestParam("dateStart")@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate dateStart, @DateTimeFormat(pattern = "yyyy-MM-dd")@RequestParam("dateEnd") LocalDate dateEnd) {
        List<Menu> menu = repository.getBetweenDates(dateStart, dateEnd);
        return getWithSumTotal(menu);
    }

    @GetMapping(value = "/ByAddressBetween")
    public List<MenuTo> getByAddressBetween(@RequestParam("address") String address, @RequestParam("dateStart")
                                        @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateStart, @RequestParam("dateEnd")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateEnd) {
        List<Menu> menu = repository.getByNameBetweenDates(address, dateStart, dateEnd);
        return getWithSumTotal(menu);
    }

    @GetMapping(value ="/ByAddress",  consumes = MediaType.ALL_VALUE)
    public List<MenuTo> getByAddress(@RequestParam("address") String address) {
        List<Menu> menu = repository.getByNameWithMeals(address);
        return getWithSumTotal(menu);
    }
}
