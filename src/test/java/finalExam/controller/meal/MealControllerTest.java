package finalExam.controller.meal;

import finalExam.TestUtil;
import finalExam.controller.AbstractControllerTest;
import finalExam.controller.json.JsonUtil;

import finalExam.matcher.BeanMatcher;
import finalExam.model.meal.Meal;

import finalExam.repository.MealRepository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;

import static finalExam.TestUtil.userHttpBasic;
import static finalExam.testData.RestaurantMealTestData.*;

import static finalExam.testData.UserTestData.ADMIN;
import static finalExam.testData.UserTestData.USER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class MealControllerTest extends AbstractControllerTest {
    private static final String REST_URL = MealRestController.REST_URL + '/';
    private static final String ADMIN_REST_URL = MealAdminRestController.REST_URL + '/';
    private final BeanMatcher<Meal> MATCHER = BeanMatcher.of(Meal.class);
    @Autowired
    private MealRepository repository;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + FIRST_RESTAURANT_ID + '/' + MEAL_FIRST_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(MEAL1));
    }


    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(ADMIN_REST_URL + FIRST_RESTAURANT_ID + '/' + MEAL_FIRST_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk());
        MATCHER.assertListEquals(Arrays.asList(MEAL2, MEAL3), repository.getAll(FIRST_RESTAURANT_ID));
    }

    @Test
    public void testGetUnauth() throws Exception {
        mockMvc.perform(get(REST_URL + FIRST_RESTAURANT_ID + '/' + MEAL_FIRST_ID))
                .andExpect(status().isUnauthorized());
    }


    @Test
    public void testUpdate() throws Exception {
        Meal updated = repository.get(MEAL_FIRST_ID, FIRST_RESTAURANT_ID);
        updated.setName("UpdatedName");
        mockMvc.perform(put(ADMIN_REST_URL+ FIRST_RESTAURANT_ID + '/'+MEAL_FIRST_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());
        MATCHER.assertEquals(updated, repository.get(MEAL_FIRST_ID, FIRST_RESTAURANT_ID));
    }

    @Test
    public void testCreate() throws Exception {
        Meal expected = new Meal(null, "New", 23.5);
        ResultActions action = mockMvc.perform(post(ADMIN_REST_URL+ FIRST_RESTAURANT_ID + '/')
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(expected))).andExpect(status().isCreated());

        Meal returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());
        MATCHER.assertEquals(expected, returned);
        MATCHER.assertListEquals(Arrays.asList(MEAL1, MEAL2, MEAL3, expected), repository.getAll(FIRST_RESTAURANT_ID));
    }

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL+ FIRST_RESTAURANT_ID + '/')
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(MEAL1,MEAL2,MEAL3)));
    }

}
