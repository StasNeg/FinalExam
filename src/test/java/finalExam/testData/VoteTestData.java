package finalExam.testData;

import finalExam.matcher.BeanMatcher;
import finalExam.model.votes.Vote;


import java.util.Objects;

import static finalExam.testData.RestaurantMealTestData.*;
import static finalExam.testData.UserTestData.ADMIN;
import static finalExam.testData.UserTestData.USER;
import static java.time.LocalDate.of;

public class VoteTestData {
    public static final Integer VOTE_FIRST_ID = MEAL10.getId() + 1;
    public static final Vote VOTE1 = new Vote(VOTE_FIRST_ID, USER, FIRST_RESTAURANT, of(2017, 10, 7));
    public static final Vote VOTE2 = new Vote(VOTE_FIRST_ID + 1, ADMIN, SECOND_RESTAURANT, of(2017, 10, 7));
    public static final Vote VOTE3 = new Vote(VOTE_FIRST_ID + 2, ADMIN, FIRST_RESTAURANT, of(2017, 10, 6));

    public static final BeanMatcher<Vote> MATCHER = BeanMatcher.of(Vote.class,
            (expected, actual) -> expected == actual || (
                    Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getDate(), actual.getDate()))
    );
}
