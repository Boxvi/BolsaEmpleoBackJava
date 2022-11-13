package ec.edu.ista.springgc1.repository;

import ec.edu.ista.springgc1.model.entity.InstitucionEducativa;
import ec.edu.ista.springgc1.repository.generic.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstEduRepository extends GenericRepository<InstitucionEducativa> {
    Optional<InstitucionEducativa> findByNombre(String nombre);
}
