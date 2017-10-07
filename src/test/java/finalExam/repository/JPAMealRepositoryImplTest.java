package finalExam.repository;

import finalExam.matcher.BeanMatcher;
import finalExam.model.meal.Meal;
import finalExam.util.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.Arrays;

import static finalExam.testData.RestaurantMealTestData.*;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class JPAMealRepositoryImplTest {
    @Autowired
    private MealRepository repository;

    private BeanMatcher<Meal> MATCHER = BeanMatcher.of(Meal.class);

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        repository.delete(MEAL_FIRST_ID, 1);
    }

    @Test
    public void testSave() throws Exception {
        Meal created = new Meal("New", 72);
        repository.save(created, THIRD_RESTAURANT_ID);
        MATCHER.assertListEquals(Arrays.asList(MEAL8, MEAL9, MEAL10, created), repository.getAll(THIRD_RESTAURANT_ID));
    }

    @Test
    public void testGet() throws Exception {
        Meal actual = repository.get(MEAL_FIRST_ID, FIRST_RESTAURANT_ID);
        MATCHER.assertEquals(MEAL1, actual);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        repository.get(MEAL_FIRST_ID, SECOND_RESTAURANT_ID);
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = new Meal("Breakfast",1000);
        updated.setId(MEAL_FIRST_ID+2);
        repository.save(updated, FIRST_RESTAURANT_ID);
        MATCHER.assertEquals(updated, repository.get(MEAL_FIRST_ID+2, FIRST_RESTAURANT_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateNotFound() throws Exception {
        repository.save(repository.get(MEAL_FIRST_ID,FIRST_RESTAURANT_ID), SECOND_RESTAURANT_ID);
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertListEquals(Arrays.asList(MEAL1,MEAL2,MEAL3), repository.getAll(FIRST_RESTAURANT_ID));
    }
}