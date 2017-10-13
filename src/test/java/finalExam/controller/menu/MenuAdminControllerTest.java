package finalExam.controller.menu;

import finalExam.TestUtil;
import finalExam.controller.AbstractControllerTest;
import finalExam.controller.json.JsonUtil;
import finalExam.controller.restaurants.RestaurantAdminRestController;
import finalExam.controller.restaurants.RestaurantRestController;
import finalExam.matcher.BeanMatcher;
import finalExam.model.menu.Menu;
import finalExam.model.restaurant.Restaurant;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static finalExam.TestUtil.userHttpBasic;
import static finalExam.testData.RestaurantMealTestData.*;
import static finalExam.testData.UserTestData.ADMIN;
import static finalExam.testData.UserTestData.USER;
import static java.time.LocalDate.now;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MenuAdminControllerTest extends AbstractControllerTest {
    private static final String REST_URL = MenuRestController.REST_URL + '/';
    private static final String ADMIN_REST_URL = MenuAdminRestController.REST_URL + '/';
    private final BeanMatcher<Menu> MATCHER = BeanMatcher.of(Menu.class);

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + FIRST_RESTAURANT_ID+"/menu/"+FIRST_MENU.getId())
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(FIRST_MENU));
    }


    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(ADMIN_REST_URL + FIRST_RESTAURANT_ID+"/menu/"+FIRST_MENU.getId())
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk());
        MATCHER.assertListEquals(Collections.singletonList(THIRTH_MENU),menuRepository.getAll(FIRST_RESTAURANT_ID));
    }

    @Test
    public void testGetUnauth() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized());
    }


    @Test
    public void testUpdate() throws Exception {
        Menu updated = menuRepository.get(FIRST_MENU.getId(),FIRST_RESTAURANT_ID);
        updated.setDate(now());
        mockMvc.perform(put(ADMIN_REST_URL+FIRST_RESTAURANT_ID+"/menu/"+FIRST_MENU.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());
        MATCHER.assertEquals(updated, menuRepository.get(FIRST_MENU.getId(),FIRST_RESTAURANT_ID));
    }

    @Test
    public void testCreate() throws Exception {
        Menu expected = new Menu(null, now(), FIRST_RESTAURANT);
        ResultActions action = mockMvc.perform(post(ADMIN_REST_URL+FIRST_RESTAURANT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(expected))).andExpect(status().isCreated());

        Menu returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());
        MATCHER.assertEquals(expected, returned);
        MATCHER.assertListEquals(Arrays.asList(
                FIRST_MENU,
                THIRTH_MENU,
                expected),
                menuRepository.getAll(FIRST_RESTAURANT_ID));
    }

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL +FIRST_RESTAURANT_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(FIRST_MENU,THIRTH_MENU)));
    }
}
