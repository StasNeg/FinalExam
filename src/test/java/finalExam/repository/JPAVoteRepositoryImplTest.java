package finalExam.repository;


import finalExam.model.votes.Vote;
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
import java.util.Collections;

import static finalExam.testData.RestaurantMealTestData.FIRST_RESTAURANT;
import static finalExam.testData.RestaurantMealTestData.SECOND_RESTAURANT;
import static finalExam.testData.UserTestData.ADMIN;
import static finalExam.testData.UserTestData.USER;
import static finalExam.testData.VoteTestData.*;
import static java.time.LocalDate.now;
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
    public void testSave() throws Exception {
        Vote vote = new Vote(SECOND_RESTAURANT, ADMIN, of(2015, 05, 30));
        Vote created = repository.save(vote);
        vote.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(VOTE1, VOTE2, vote), repository.getAll());
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateSave() throws Exception {
        Vote vote = new Vote(SECOND_RESTAURANT, USER, of(2015, 05, 30));
        Vote created = repository.save(vote);
        vote.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(VOTE1, VOTE2, vote), repository.getAll());
    }

    @Test
    public void get() throws Exception {
        Vote vote = repository.get(VOTE_FIRST_ID);
        MATCHER.assertEquals(VOTE1, vote);
    }

    @Test
    public void getNotFound() throws Exception {
        Vote vote = repository.get(1);
        MATCHER.assertEquals(null, vote);
    }


    @Test
    public void delete() throws Exception {
        repository.delete(VOTE_FIRST_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(VOTE2), repository.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        repository.delete(1);
    }

    @Test
    public void testUpdate() throws Exception {
        Vote updated = repository.get(VOTE_FIRST_ID);
        updated.setDate(now());
        repository.save(updated);
        MATCHER.assertEquals(updated, repository.get(VOTE_FIRST_ID));
    }

    @Test
    public void testGetUserByDate() throws Exception {
        Vote vote = new Vote(FIRST_RESTAURANT, ADMIN, of(2015, 05, 30));
        Vote created = repository.save(vote);
        repository.getAllRestaurantByDay(of(2015,05,30)).forEach(x->{
            System.out.println(x);
        });
        repository.getAllUserByDay(of(2015,05,30)).forEach(x->
            System.out.println(x)
        );
//        MATCHER.assertEquals(updated, repository.get(VOTE_FIRST_ID));
    }

}