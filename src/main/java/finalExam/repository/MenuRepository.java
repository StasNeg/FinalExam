package finalExam.repository;

import finalExam.model.menu.Menu;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Stanislav on 10.10.2017.
 */
public interface MenuRepository {

    Menu get(Integer id,Integer restaurantId);

    void delete(Integer id, Integer restaurantId);

    Menu save(Menu menu, Integer restaurantId);

    public List<Menu> getByNameWithMeals(String name);

    public List<Menu> getBetweenDates(LocalDate startDate, LocalDate endDate);

    public List<Menu> getByNameBetweenDates(String name, LocalDate startDate, LocalDate endDate);

    List<Menu> getAll(Integer restaurantId);

    public void evictCache();
}
