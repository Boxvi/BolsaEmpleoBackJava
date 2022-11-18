package ec.edu.ista.springgc1.repository;

import ec.edu.ista.springgc1.model.entity.Postulacion;
import ec.edu.ista.springgc1.repository.generic.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PostulacionRepository extends GenericRepository<Postulacion> {
    @Query(value = "SELECT CASE WHEN EXISTS (select p.estado, e.nombres, e.apellidos from postulaciones p inner join estudiante e on p.estudiante_id = e.estudiante_id inner join ofertaslaborales ol on p.oferta_id = ol.oferta_id where e.cedula = :cedula and ol.oferta_id = :oferta_id)THEN 1 ELSE 0 END;", nativeQuery = true)
    public Integer thereIsAnApplicationFromThisStudentToThisOffer(String cedula, long oferta_id);

}


