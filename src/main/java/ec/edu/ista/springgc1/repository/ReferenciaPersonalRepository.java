package ec.edu.ista.springgc1.repository;

import ec.edu.ista.springgc1.model.entity.ReferenciaPersonal;
import ec.edu.ista.springgc1.model.entity.ReferenciaProfesional;
import ec.edu.ista.springgc1.repository.generic.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReferenciaPersonalRepository extends GenericRepository<ReferenciaPersonal> {


    Optional<ReferenciaPersonal> findByNombre(String nombre);

    @Query(value = "select * from referenciaPersonal where estudiante_id = :estudiante_id", nativeQuery = true)
    List<ReferenciaPersonal> findByEstudiante(long estudiante_id);

}
