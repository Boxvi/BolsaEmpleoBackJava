package ec.edu.ista.springgc1.repository;

import ec.edu.ista.springgc1.repository.generic.GenericRepository;
import ec.edu.ista.springgc1.model.entity.Educacion;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EducacionRepository  extends GenericRepository<Educacion> {

    Optional<Educacion> findByTitulo(String titulo);
}
