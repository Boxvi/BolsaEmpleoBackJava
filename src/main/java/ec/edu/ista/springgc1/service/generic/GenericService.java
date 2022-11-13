package ec.edu.ista.springgc1.service.generic;

import java.util.List;

public interface GenericService<T> {
    List<T> findAll();
    T save(T entity);
    void delete(Long id);
}
