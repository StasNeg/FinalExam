package finalExam.controller.restaurant;

import finalExam.TestUtil;
import finalExam.controller.AbstractControllerTest;
import finalExam.controller.json.JsonUtil;
import finalExam.controller.restaurants.RestaurantAdminRestController;
import finalExam.controller.restaurants.RestaurantRestController;
import finalExam.matcher.BeanMatcher;
import finalExam.model.restaurant.Restaurant;
import finalExam.to.MenuTo;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;

import static finalExam.TestUtil.userHttpBasic;
import static finalExam.testData.RestaurantMealTestData.*;

import static finalExam.testData.UserTestData.ADMIN;

import static java.time.LocalDate.now;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminRestaurantControllerTest extends AbstractControllerTest {
    private static final String REST_URL = RestaurantRestController.REST_URL + '/';
    private static final String ADMIN_REST_URL = RestaurantAdminRestController.REST_URL + '/';
    private final BeanMatcher<Restaurant> MATCHER = BeanMatcher.of(Restaurant.class);

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + FIRST_RESTAURANT_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(FIRST_RESTAURANT));
    }


    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(ADMIN_REST_URL + FIRST_RESTAURANT_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk());
        MATCHER.assertListEquals(Arrays.asList(SECOND_RESTAURANT),restaurantRepository.getAll());
    }

    @Test
    public void testGetUnauth() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized());
    }


    @Test
    public void testUpdate() throws Exception {
        Restaurant updated = restaurantRepository.get(FIRST_RESTAURANT_ID);
        updated.setName("UpdatedName");
        mockMvc.perform(put(ADMIN_REST_URL+FIRST_RESTAURANT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());
        MATCHER.assertEquals(updated, restaurantRepository.get(FIRST_RESTAURANT_ID));
    }

    @Test
    public void testCreate() throws Exception {
        Restaurant expected = new Restaurant(null, "New", "NEW");
        ResultActions action = mockMvc.perform(post(ADMIN_REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(expected))).andExpect(status().isCreated());

        Restaurant returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());
        MATCHER.assertEquals(expected, returned);
        MATCHER.assertListEquals(Arrays.asList(
                FIRST_RESTAURANT,
                SECOND_RESTAURANT,

                expected),
                restaurantRepository.getAll());
    }

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(FIRST_RESTAURANT, SECOND_RESTAURANT)));
    }
}
