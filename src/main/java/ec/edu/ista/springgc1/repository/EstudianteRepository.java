package ec.edu.ista.springgc1.repository;

import ec.edu.ista.springgc1.model.entity.Estudiante;
import ec.edu.ista.springgc1.repository.generic.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstudianteRepository extends GenericRepository<Estudiante> {

    Optional<Estudiante> findByNombres(String nombres);

    @Query(value = "select * from estudiante where usuario_id = :usuario_id", nativeQuery = true)
    Optional<Estudiante> findByUsuario(long usuario_id);

    Boolean existsByCedula(String cedula);

    Optional<Estudiante> findByCedula(String cedula);
}
