package ec.edu.ista.springgc1.repository;


import ec.edu.ista.springgc1.model.entity.Nivel;
import ec.edu.ista.springgc1.repository.generic.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NivelRepository extends GenericRepository<Nivel> {

    Optional<Nivel> findByNombre(String nombre);

}
