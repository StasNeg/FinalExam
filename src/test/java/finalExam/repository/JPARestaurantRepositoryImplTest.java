package finalExam.repository;

import finalExam.matcher.BeanMatcher;
import finalExam.model.restaurant.Restaurant;
import finalExam.util.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



import java.util.*;

import static finalExam.testData.RestaurantMealTestData.*;
import static java.time.LocalDate.of;


/**
 * Created by Stanislav on 01.10.2017.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class JPARestaurantRepositoryImplTest {
    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private RestaurantRepository repository;
    @Autowired
    private MealRepository mealRepository;

    private BeanMatcher<Restaurant> MATCHER = BeanMatcher.of(Restaurant.class);

    @Test
    @Rollback(false)
    public void testSave() throws Exception {
        Restaurant newRestaurant = new Restaurant(null, "New", "NEW ADDRESS", of(2015, 06, 01));
        Restaurant created = repository.save(newRestaurant);
        newRestaurant.setId(created.getId());
        System.out.println(repository.getAll());
        MATCHER.assertListEquals(Arrays.asList(FIRST_RESTAURANT,SECOND_RESTAURANT,THIRD_RESTAURANT, newRestaurant), repository.getAll());
    }

    @Test
    public void getAll() throws Exception {
        List<Restaurant> all = repository.getAll();
        MATCHER.assertListEquals(Arrays.asList(FIRST_RESTAURANT , SECOND_RESTAURANT,THIRD_RESTAURANT), all);
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateMailSave() throws Exception {
        repository.save(new Restaurant(null, "Астория", "Первого восстания 78", of(2015, 05, 30)));
    }

    @Test
    public void testDelete() throws Exception {
        repository.delete(SECOND_RESTAURANT_ID);
        MATCHER.assertListEquals(Arrays.asList(FIRST_RESTAURANT , THIRD_RESTAURANT), repository.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        repository.delete(1);
    }

    @Test
    public void testGet() throws Exception {
        Restaurant restaurant = repository.get(SECOND_RESTAURANT_ID);
        MATCHER.assertEquals(SECOND_RESTAURANT, restaurant);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        repository.get(1);
    }

    @Rollback(false)
    @Test
    public void testUpdate() throws Exception {
        Restaurant updated = repository.get(FIRST_RESTAURANT_ID);
        updated.setName("UpdatedName");
        repository.save(updated);
        MATCHER.assertEquals(updated, repository.get(FIRST_RESTAURANT_ID));
    }

    @Test
    public void testGetAlLWithMeals() throws Exception {
        List<Restaurant> restaurants = repository.getByNameWithMeals(FIRST_RESTAURANT.getName());
        System.out.println(restaurants);
        restaurants = repository.getByNameBetweenDates(SECOND_RESTAURANT.getName(), of(2015, 01, 01), of(2015, 12, 30));
        System.out.println(restaurants);
        restaurants = repository.getBetweenDates(of(2015, 01, 01), of(2015, 12, 30));
        System.out.println(restaurants);
    }
}