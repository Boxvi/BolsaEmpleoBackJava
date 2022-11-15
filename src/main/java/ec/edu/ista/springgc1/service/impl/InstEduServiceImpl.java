package ec.edu.ista.springgc1.service.impl;

import ec.edu.ista.springgc1.model.entity.InstitucionEducativa;
import ec.edu.ista.springgc1.repository.InstEduRepository;
import ec.edu.ista.springgc1.service.generic.impl.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InstEduServiceImpl extends GenericServiceImpl<InstitucionEducativa> {

    @Autowired
    private InstEduRepository instEduRepository;

    public Optional<InstitucionEducativa> findByNombre(String nombre){
        return instEduRepository.findByNombre(nombre);
    }
}
