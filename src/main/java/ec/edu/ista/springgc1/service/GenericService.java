package ec.edu.ista.springgc1.service;

import java.io.Serializable;
import java.util.Optional;

public interface GenericService<T, ID> extends Serializable {

    T save(T t);

    Iterable<T> findAll();

    void deleteById(ID id);

    Optional<T> findById(ID id);
}
