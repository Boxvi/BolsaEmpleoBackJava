package ec.edu.ista.springgc1.repository;

import ec.edu.ista.springgc1.repository.generic.GenericRepository;
import ec.edu.ista.springgc1.model.entity.Educacion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EducacionRepository  extends GenericRepository<Educacion> {

    Optional<Educacion> findByTitulo(String titulo);
    @Query(value = "select * from educacion where estudiante_id = :estudiante_id", nativeQuery = true)
    List<Educacion> findByEstudiante(long estudiante_id);
}
