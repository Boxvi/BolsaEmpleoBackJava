package ec.edu.ista.springgc1.repository;

import ec.edu.ista.springgc1.model.entity.ReferenciaPersonal;
import ec.edu.ista.springgc1.repository.generic.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReferenciaPersonalRepository extends GenericRepository<ReferenciaPersonal> {


    Optional<ReferenciaPersonal> findByNombre(String nombre);

}
