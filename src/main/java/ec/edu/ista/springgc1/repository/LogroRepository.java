package ec.edu.ista.springgc1.repository;


import ec.edu.ista.springgc1.model.dto.LogroDTO;
import ec.edu.ista.springgc1.model.entity.Capacitacion;
import ec.edu.ista.springgc1.model.entity.Logro;
import ec.edu.ista.springgc1.repository.generic.GenericRepository;
import ec.edu.ista.springgc1.service.map.Mapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LogroRepository extends GenericRepository<Logro>  {


    Optional<Logro> findByTipoLogro(String tipoLogro);

    @Query(value = "select * from logro where estudiante_id = :estudiante_id", nativeQuery = true)
    List<Logro> findByEstudiante(long estudiante_id);


}
