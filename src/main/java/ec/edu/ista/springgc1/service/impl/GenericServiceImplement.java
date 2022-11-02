package ec.edu.ista.springgc1.service.impl;

import ec.edu.ista.springgc1.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Optional;

@Service
public abstract class GenericServiceImplement <T,ID extends Serializable> implements GenericService<T, ID> {

    @Autowired
    public abstract JpaRepository<T,ID> jpaRepository();

    @Override
    public T save(T t) {
        return jpaRepository().save(t);
    }

    @Override
    public Iterable<T> findAll() {
        return jpaRepository().findAll();
    }

    @Override
    public void deleteById(ID id) {
        jpaRepository().deleteById(id);
    }

    @Override
    public Optional<T> findById(ID id) {
        return jpaRepository().findById(id);
    }
}
