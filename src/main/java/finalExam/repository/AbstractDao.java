package finalExam.repository;
import java.io.Serializable;

public interface AbstractDao<T, K extends Serializable> {
    T create(T created);
    T update (T edit);
    void delete(K id);
    T get(K id);
}
