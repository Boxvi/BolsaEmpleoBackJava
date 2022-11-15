package ec.edu.ista.springgc1.repository;

import ec.edu.ista.springgc1.model.entity.Estudiante;
import ec.edu.ista.springgc1.repository.generic.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstudianteRepository extends GenericRepository<Estudiante> {

    Optional<Estudiante> findByNombres(String nombres);

    Boolean existsByCedula(String cedula);
}
