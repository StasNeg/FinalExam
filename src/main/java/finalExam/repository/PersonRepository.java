package finalExam.repository;
import finalExam.model.users.Person;

import java.util.List;

public interface PersonRepository {
    List<Person> getAll();

    Person get(Integer id);

    boolean delete(Integer id);

    Person save(Person person);
}
