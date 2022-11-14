package ec.edu.ista.springgc1.service.impl;

import ec.edu.ista.springgc1.model.entity.Nivel;
import ec.edu.ista.springgc1.repository.NivelRepository;
import ec.edu.ista.springgc1.service.generic.impl.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NivelServiceImpl extends GenericServiceImpl<Nivel> {

    @Autowired
    private NivelRepository nivelRepository;

    public Optional<Nivel> findByNombre(String nombre){
        return nivelRepository.findByNombre(nombre);
    }

}
