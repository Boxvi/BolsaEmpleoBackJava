package ec.edu.ista.springgc1.repository;

import ec.edu.ista.springgc1.repository.generic.GenericRepository;
import ec.edu.ista.springgc1.model.entity.PerfilOcupacional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerfilOcupacionalRepository extends GenericRepository<PerfilOcupacional> {

    @Query(value = "select * from perfiles_ocupacional where estudiante_id = :estudiante_id ;", nativeQuery = true)
    public Optional<PerfilOcupacional> findByEstudiante(long estudiante_id);


    @Query(value = "SELECT CASE WHEN EXISTS (select pe.* from perfiles_ocupacional pe inner join estudiante e on pe.estudiante_id = e.estudiante_id where e.cedula = :cedula)THEN 1 ELSE 0 END", nativeQuery = true)
    Integer existsAlreadyAPerfilOcupacionalwithThisDNI(String cedula);


}
