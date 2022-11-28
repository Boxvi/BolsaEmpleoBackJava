package ec.edu.ista.springgc1.repository;
import ec.edu.ista.springgc1.repository.generic.GenericRepository;
import ec.edu.ista.springgc1.model.entity.PreferenciaEmpleo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PreferenciaEmpleoRepository  extends GenericRepository<PreferenciaEmpleo> {

    @Query(value = "select * from preferencia_empleos where estudiante_id = :estudiante_id ;",nativeQuery = true)
     List<PreferenciaEmpleo> findByEstudiante(long estudiante_id);

    @Query(value = "SELECT CASE WHEN EXISTS (select pe.* from preferencia_empleos pe inner join estudiante e on pe.estudiante_id = e.estudiante_id where e.cedula = :cedula)THEN 1 ELSE 0 END",nativeQuery = true)
    Integer existAlreadyAPreferenciaEmpleoWithThisDNI(String cedula);

}
