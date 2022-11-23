package ec.edu.ista.springgc1.repository;

import ec.edu.ista.springgc1.repository.generic.GenericRepository;
import ec.edu.ista.springgc1.model.entity.PerfilOcupacional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerfilOcupacionalRepository  extends GenericRepository<PerfilOcupacional> {

    @Query(value = "select * from perfiles_ocupacional where estudiante_id = :estudiante_id ;",nativeQuery = true)
    public Optional<PerfilOcupacional> findByEstudiante(long estudiante_id);



}
