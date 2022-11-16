package ec.edu.ista.springgc1.repository;

import ec.edu.ista.springgc1.model.entity.ReferenciaProfesional;
import org.springframework.stereotype.Repository;
import ec.edu.ista.springgc1.repository.generic.GenericRepository;

import java.util.Optional;

@Repository
public interface ReferenciaProfesionalRepository extends GenericRepository<ReferenciaProfesional> {

    Optional<ReferenciaProfesional> findByInstitucion(String institucion);


}
