package finalExam.testData;

import finalExam.matcher.BeanMatcher;
import finalExam.model.votes.Vote;


import java.util.Objects;

import static finalExam.testData.RestaurantMealTestData.FIRST_RESTAURANT;
import static finalExam.testData.RestaurantMealTestData.MEAL10;
import static finalExam.testData.RestaurantMealTestData.SECOND_RESTAURANT;
import static finalExam.testData.UserTestData.USER;
import static java.time.LocalDate.of;

public class VoteTestData {
    public static final Integer VOTE_FIRST_ID = MEAL10.getId() + 1;
    public static final Vote VOTE1 = new Vote(VOTE_FIRST_ID, FIRST_RESTAURANT, USER, of(2015, 05, 30));
    public static final Vote VOTE2 = new Vote(VOTE_FIRST_ID + 1, SECOND_RESTAURANT, USER, of(2015, 05, 31));

    public static final BeanMatcher<Vote> MATCHER = new BeanMatcher<>(
            (expected, actual) -> expected == actual || (
                    Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getDate(), actual.getDate())
            )
    );
}
