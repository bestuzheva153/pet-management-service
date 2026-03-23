package example.interfaces;

import java.util.List;

public interface GenericDao<T> {
    T save(T entity);
    void deleteById(long id);
    void deleteByEntity(T entity);
    void deleteAll();
    T update(T entity);
    T getById(long id);
    List<T> getAll();
}