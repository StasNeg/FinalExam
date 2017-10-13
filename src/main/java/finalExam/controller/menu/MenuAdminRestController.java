package finalExam.controller.menu;
import finalExam.model.menu.Menu;
import finalExam.repository.MenuRepository;
import finalExam.to.MenuTo;
import finalExam.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static finalExam.util.MenuUtil.getWithSumTotal;


@RestController
@RequestMapping(value = MenuAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuAdminRestController {
    static final String REST_URL = "/rest/admin/";
    @Autowired
    private MenuRepository repository;

    @DeleteMapping("/{restaurantId}/menu/{id}")
    public void delete(@PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) {
        repository.delete(id, restaurantId);
    }

    @PutMapping(value = "/{restaurantId}/menu/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public MenuTo update(@RequestBody Menu menu, @PathVariable("restaurantId") int restaurantId, @PathVariable("id") int id) {
        ValidationUtil.assureIdConsistent(menu, id);
        return getWithSumTotal(repository.save(menu, restaurantId));
    }

    @PostMapping(value = "/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MenuTo> createWithLocation(@RequestBody Menu menu, @PathVariable("restaurantId") int restaurantId) {
        if (menu.isNew()) {
            Menu created = repository.save(menu, restaurantId);
            URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(REST_URL + "/{id}")
                    .buildAndExpand(created.getId()).toUri();
            return ResponseEntity.created(uriOfNewResource).body(getWithSumTotal(created));
        }
        return null;
    }
}
