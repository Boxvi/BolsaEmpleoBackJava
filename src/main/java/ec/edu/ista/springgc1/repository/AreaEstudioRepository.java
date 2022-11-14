package ec.edu.ista.springgc1.repository;

import ec.edu.ista.springgc1.model.entity.AreaEstudio;
import ec.edu.ista.springgc1.model.entity.InstitucionEducativa;
import ec.edu.ista.springgc1.repository.generic.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AreaEstudioRepository extends GenericRepository<AreaEstudio>{

   Optional<AreaEstudio> findByNombre(String nombre);

}
