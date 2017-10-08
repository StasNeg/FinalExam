package finalExam.repository;
import finalExam.model.user.User;

import java.util.List;

public interface UserRepository {
    List<User> getAll();

    User get(Integer id);

    void delete(Integer id);

    User save(User user);

    User getByEmail(String email);

    void evictCache();

}
