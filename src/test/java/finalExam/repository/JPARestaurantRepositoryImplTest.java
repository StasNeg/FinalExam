package finalExam.repository;

import finalExam.matcher.BeanMatcher;
import finalExam.model.restaurant.Restaurant;
import finalExam.util.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static finalExam.testData.RestaurantTestData.*;
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

    private BeanMatcher<Restaurant> MATCHER = new BeanMatcher<>();

    @Test
    public void testSave() throws Exception {
        Restaurant newRestaurant = new Restaurant(null, "New", of(2015,06,01),  Arrays.asList(MEAL1, MEAL6, MEAL7));
        Restaurant created = repository.save(newRestaurant);
        newRestaurant.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(FIRST_RESTAURANT,SECOND_RESTAURANT, newRestaurant), repository.getAll());
    }

    @Test
    public void getAll() throws Exception {
        Collection<Restaurant> all = repository.getAll();
        MATCHER.assertCollectionEquals(Arrays.asList(FIRST_RESTAURANT , SECOND_RESTAURANT), all);
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateMailSave() throws Exception {
        repository.save(new Restaurant(null, "Континенталь", of(2015,05,30),
                Arrays.asList(MEAL1, MEAL2, MEAL3)));
    }

    @Test
    public void testDelete() throws Exception {
        repository.delete(SECOND_RESTAURANT_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(FIRST_RESTAURANT), repository.getAll());
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


    @Test
    public void testUpdate() throws Exception {
        Restaurant updated = repository.get(FIRST_RESTAURANT_ID);
        updated.setName("UpdatedName");
        repository.save(updated);
        MATCHER.assertEquals(updated, repository.get(FIRST_RESTAURANT_ID));
    }
}