package ec.edu.ista.springgc1.repository;

import ec.edu.ista.springgc1.model.entity.Educacion;
import ec.edu.ista.springgc1.model.entity.ReferenciaProfesional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ec.edu.ista.springgc1.repository.generic.GenericRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReferenciaProfesionalRepository extends GenericRepository<ReferenciaProfesional> {

    Optional<ReferenciaProfesional> findByInstitucion(String institucion);

    @Query(value = "select * from referenciaProfesional where estudiante_id = :estudiante_id", nativeQuery = true)
    List<ReferenciaProfesional> findByEstudiante(long estudiante_id);


}
