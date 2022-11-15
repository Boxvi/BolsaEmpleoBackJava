package ec.edu.ista.springgc1.service.impl;


import ec.edu.ista.springgc1.model.entity.AreaEstudio;
import ec.edu.ista.springgc1.repository.AreaEstudioRepository;
import ec.edu.ista.springgc1.service.generic.impl.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AreaEstudioServiceImpl extends GenericServiceImpl<AreaEstudio> {

   @Autowired
    private AreaEstudioRepository areaEstudioRepository;

    public Optional<AreaEstudio> findByNombre(String nombre){
        return areaEstudioRepository.findByNombre(nombre);
    }
}
