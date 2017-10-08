package finalExam.testData;

import finalExam.matcher.BeanMatcher;
import finalExam.model.user.Role;
import finalExam.model.user.User;
import java.util.Objects;

import static finalExam.model.IdNamedAbstractClass.START_SEQ;


public class UserTestData {
    public static final Integer USER_ID = START_SEQ;
    public static final Integer ADMIN_ID = START_SEQ + 1;

    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "password", Role.ROLE_ADMIN);

    public static final BeanMatcher<User> MATCHER = BeanMatcher.of(User.class,
            (expected, actual) -> expected == actual ||
                    (
                            Objects.equals(expected.getPassword(), actual.getPassword())
                             &&Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName())
                            && Objects.equals(expected.getEmail(), actual.getEmail())
                            && Objects.equals(expected.getRoles(), actual.getRoles())
                    )
    );
}
