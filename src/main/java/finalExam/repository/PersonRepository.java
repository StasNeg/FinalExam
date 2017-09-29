package finalExam.repository;
import finalExam.model.users.User;

import java.util.List;

public interface PersonRepository {
    List<User> getAll();

    User get(Integer id);

    boolean delete(Integer id);

    User save(User user);
}
