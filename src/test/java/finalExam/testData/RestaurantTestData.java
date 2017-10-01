package finalExam.testData;

import finalExam.model.meal.Meal;
import finalExam.model.restaurant.Restaurant;

import java.time.LocalDate;
import java.util.Arrays;


import static finalExam.model.IdNamedAbstractClass.START_SEQ;

/**
 * Created by Stanislav on 01.10.2017.
 */
public class RestaurantTestData {
    public static final Integer FIRST_RESTAURANT_ID = START_SEQ + 2;
    public static final Integer SECOND_RESTAURANT_ID = START_SEQ + 3;
    public static final Meal MEAL1 = new Meal(FIRST_RESTAURANT_ID+2,"Сок", 33);
    public static final Meal MEAL2 = new Meal(FIRST_RESTAURANT_ID+3,"Суп Харчо", 62);
    public static final Meal MEAL3 = new Meal(FIRST_RESTAURANT_ID+4,"Пельмени", 62);
    public static final Meal MEAL4 = new Meal(FIRST_RESTAURANT_ID+5,"Жаркое", 78);
    public static final Meal MEAL5 = new Meal(FIRST_RESTAURANT_ID+6,"Сок", 33);
    public static final Meal MEAL6 = new Meal(FIRST_RESTAURANT_ID+7,"Суп Харчо", 62);
    public static final Meal MEAL7 = new Meal(FIRST_RESTAURANT_ID+8,"Пельмени", 62);


    //    public Restaurant(Integer id, String name, LocalDate date, List<Meal> meals) {
    public static final Restaurant FIRST_RESTAURANT = new Restaurant(FIRST_RESTAURANT_ID,"Континенталь", LocalDate.of(2015,05,30),
        Arrays.asList(MEAL1, MEAL2, MEAL3));
    public static final Restaurant SECOND_RESTAURANT = new Restaurant(SECOND_RESTAURANT_ID,"Астория", LocalDate.of(2015,05,30),
            Arrays.asList(MEAL4, MEAL5, MEAL6, MEAL7 ));


}
