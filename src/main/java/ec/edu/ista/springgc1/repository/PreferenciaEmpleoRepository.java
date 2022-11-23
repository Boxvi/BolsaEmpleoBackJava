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

}
