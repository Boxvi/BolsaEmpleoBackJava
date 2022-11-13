package ec.edu.ista.springgc1.service.generic.impl;

import ec.edu.ista.springgc1.repository.generic.GenericRepository;
import ec.edu.ista.springgc1.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GenericServiceImpl <T> implements GenericService {

    @Autowired
    protected GenericRepository<T> genericRepository;

    @Override
    public List findAll() {
        return genericRepository.findAll();
    }

    @Override
    public T save(Object entity){
        return genericRepository.save((T)entity);
    }

    @Override
    public void delete(Long id){
        genericRepository.deleteById(id);
    }

}
