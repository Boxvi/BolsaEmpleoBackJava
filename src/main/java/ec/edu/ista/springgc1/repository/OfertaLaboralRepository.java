package ec.edu.ista.springgc1.repository;

import ec.edu.ista.springgc1.model.entity.OfertaLaboral;
import ec.edu.ista.springgc1.repository.generic.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OfertaLaboralRepository extends GenericRepository<OfertaLaboral> {


    Optional<OfertaLaboral> findByCargo(String cargo);

}
