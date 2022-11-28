package ec.edu.ista.springgc1.repository;

import ec.edu.ista.springgc1.model.entity.Capacitacion;
import ec.edu.ista.springgc1.model.entity.ReferenciaPersonal;
import ec.edu.ista.springgc1.repository.generic.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CapacitacionRepository extends GenericRepository<Capacitacion> {

    Optional<Capacitacion> findByInstitucion(String institucion);

    @Query(value = "select * from capacitacion c inner join estudiante e on c.estudiante_id = e.estudiante_id where e.cedula = :cedula ;", nativeQuery = true)
    public List<Capacitacion> findByCedulaCapacitacions (String cedula);

    @Query(value = "select * from capacitacion where estudiante_id = :estudiante_id", nativeQuery = true)
    List<Capacitacion> findByEstudiante(long estudiante_id);


}
