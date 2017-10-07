package finalExam.repository;

import finalExam.matcher.BeanMatcher;
import finalExam.model.restaurant.Restaurant;
import finalExam.model.votes.Vote;
import finalExam.testData.UserTestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.Arrays;
import java.util.List;

import static finalExam.testData.RestaurantMealTestData.*;
import static finalExam.testData.UserTestData.ADMIN;

import static finalExam.testData.UserTestData.USER;
import static finalExam.testData.VoteTestData.*;
import static java.time.LocalDate.of;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class JPAVoteRepositoryImplTest {
    static {
        SLF4JBridgeHandler.install();
    }
    @Autowired
    private VoteRepository repository;

    @Test
    public void testGetAll() throws Exception {
        List<Vote> votes = repository.getAll();
        MATCHER.assertListEquals(Arrays.asList(VOTE1, VOTE2, VOTE3), votes);
    }

    @Test
    public void testSave() throws Exception {
        Vote created = repository.save(SECOND_RESTAURANT, ADMIN);
        MATCHER.assertListEquals(Arrays.asList(VOTE1, created, VOTE3), repository.getAll());
    }

    @Test
    public void testSaveWithOneParam() throws Exception {
        Vote created = repository.save(SECOND_RESTAURANT_ID, ADMIN);
        MATCHER.assertListEquals(Arrays.asList(VOTE1, created, VOTE3), repository.getAll());
    }

    @Test
    public void testGetUserByDate() throws Exception {
        UserTestData.MATCHER.assertListEquals(Arrays.asList(USER,ADMIN),repository.getAllUserByDay(of(2017, 10, 7)));
    }

    @Test
    public void testGetRestaurantByDate() throws Exception {
        BeanMatcher <Restaurant> RestaurantMatcher = BeanMatcher.of(Restaurant.class);
        List<Restaurant> restaurants = repository.getAllRestaurantByDay(of(2017, 10, 7));
        RestaurantMatcher.assertListEquals(Arrays.asList(FIRST_RESTAURANT,SECOND_RESTAURANT),restaurants);
    }

}