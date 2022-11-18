package ec.edu.ista.springgc1.repository;

import ec.edu.ista.springgc1.model.entity.Capacitacion;
import ec.edu.ista.springgc1.repository.generic.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CapacitacionRepository extends GenericRepository<Capacitacion> {

    Optional<Capacitacion> findByInstitucion(String institucion);

}
