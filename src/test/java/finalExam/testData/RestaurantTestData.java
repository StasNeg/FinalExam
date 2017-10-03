package finalExam.testData;

import finalExam.model.meal.Meal;
import finalExam.model.restaurant.Restaurant;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;


import static finalExam.model.IdNamedAbstractClass.START_SEQ;

/**
 * Created by Stanislav on 01.10.2017.
 */
public class RestaurantTestData {
    public static final Integer FIRST_RESTAURANT_ID = START_SEQ + 2;
    public static final Integer SECOND_RESTAURANT_ID = START_SEQ + 3;
    public static final Integer THIRD_RESTAURANT_ID = START_SEQ + 4;
    public static final Meal MEAL1 = new Meal(FIRST_RESTAURANT_ID+3,"Сок", 33);
    public static final Meal MEAL2 = new Meal(FIRST_RESTAURANT_ID+4,"Суп Харчо", 62);
    public static final Meal MEAL3 = new Meal(FIRST_RESTAURANT_ID+5,"Пельмени", 62);
    public static final Meal MEAL4 = new Meal(FIRST_RESTAURANT_ID+6,"Жаркое", 78);
    public static final Meal MEAL5 = new Meal(FIRST_RESTAURANT_ID+7,"Сок", 33);
    public static final Meal MEAL6 = new Meal(FIRST_RESTAURANT_ID+8,"Суп Харчо", 62);
    public static final Meal MEAL7 = new Meal(FIRST_RESTAURANT_ID+9,"Пельмени", 62);
    public static final Meal MEAL8 = new Meal(FIRST_RESTAURANT_ID+10,"Сок", 33);
    public static final Meal MEAL9 = new Meal(FIRST_RESTAURANT_ID+11,"Суп Харчо", 62);
    public static final Meal MEAL10 = new Meal(FIRST_RESTAURANT_ID+12,"Пельмени", 62);

    //    public Restaurant(Integer id, String name, LocalDate date, List<Meal> meals) {
    public static final Restaurant FIRST_RESTAURANT = new Restaurant(FIRST_RESTAURANT_ID,"Континенталь", LocalDate.of(2015,05,30),
            Arrays.asList(MEAL1,MEAL2,MEAL3).stream().sorted((m1,m2)->(Double.compare(m1.getPrice(),m2.getPrice()))).collect(Collectors.toList()));
    public static final Restaurant SECOND_RESTAURANT = new Restaurant(SECOND_RESTAURANT_ID,"Астория", LocalDate.of(2015,05,30),
            Arrays.asList(MEAL4,MEAL5,MEAL6,MEAL7).stream().sorted((m1,m2)->(Double.compare(m1.getPrice(),m2.getPrice()))).collect(Collectors.toList()));
    public static final Restaurant THIRD_RESTAURANT = new Restaurant(THIRD_RESTAURANT_ID,"Астория", LocalDate.of(2015,05,31),
            Arrays.asList(MEAL8,MEAL9,MEAL10).stream().sorted((m1,m2)->(Double.compare(m1.getPrice(),m2.getPrice()))).collect(Collectors.toList()));

}
