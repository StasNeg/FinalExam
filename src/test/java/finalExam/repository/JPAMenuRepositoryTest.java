package finalExam.repository;

import finalExam.matcher.BeanMatcher;
import finalExam.model.menu.Menu;
import finalExam.util.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static finalExam.testData.RestaurantMealTestData.*;
import static java.time.LocalDate.now;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class JPAMenuRepositoryTest {
    static {
        SLF4JBridgeHandler.install();
    }
    private BeanMatcher<Menu> MATCHER = BeanMatcher.of(Menu.class);
    @Before
    public void setUp() {
        repository.evictCache();
    }

    @Autowired
    private MenuRepository repository;


    @Test
    public void testSave() throws Exception {
        Menu menu = new Menu(null, now());
        Menu created = repository.save(menu, FIRST_RESTAURANT_ID);
        menu.setId(created.getId());
        MATCHER.assertListEquals(Arrays.asList(FIRST_MENU,THIRTH_MENU, menu), repository.getAll(FIRST_RESTAURANT_ID));
    }

    @Test
    public void getAll() throws Exception {
        List<Menu> all = repository.getAll(SECOND_RESTAURANT_ID);
        MATCHER.assertListEquals(Arrays.asList(SECOND_MENU, FORTH_MENU), all);
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateMailSave() throws Exception {
        repository.save(new Menu(null, LocalDate.of(2015,05,30)),FIRST_RESTAURANT_ID);
    }

    @Test
    public void testDelete() throws Exception {
        repository.delete(FIRST_MENU.getId(),FIRST_RESTAURANT_ID);
        MATCHER.assertListEquals(Collections.singletonList(THIRTH_MENU), repository.getAll(FIRST_RESTAURANT_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        repository.delete(1,1);
    }

    @Test
    public void testGet() throws Exception {
        Menu menu = repository.get(FIRST_MENU.getId(),FIRST_RESTAURANT_ID);
        MATCHER.assertEquals(FIRST_MENU, menu);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        repository.get(1,1);
    }

    @Test
    public void testGetByAddress() throws Exception {
        List<Menu> menus = repository.getByNameWithMeals("каб");
        MATCHER.assertListEquals(Arrays.asList(FIRST_MENU,THIRTH_MENU), menus);
    }

    @Test
    public void testUpdate() throws Exception {
        Menu updated = repository.get(THIRTH_MENU.getId(),FIRST_RESTAURANT_ID);
        updated.setDate(LocalDate.now());
        repository.save(updated,FIRST_RESTAURANT_ID);
        MATCHER.assertEquals(updated, repository.get(THIRTH_MENU.getId(),FIRST_RESTAURANT_ID));
    }

}
