package finalExam.repository;
import finalExam.model.users.User;
import finalExam.model.votes.Vote;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository {
    List<User> getAll();

    User get(Integer id);

    void delete(Integer id);

    User save(User user);

    User getByEmail(String email);

    void evictCache();

}
