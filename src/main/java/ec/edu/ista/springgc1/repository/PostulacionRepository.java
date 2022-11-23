package ec.edu.ista.springgc1.repository;

import ec.edu.ista.springgc1.model.entity.Postulacion;
import ec.edu.ista.springgc1.repository.generic.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostulacionRepository extends GenericRepository<Postulacion> {
    @Query(value = "SELECT CASE WHEN EXISTS (select p.estado, e.nombres, e.apellidos from postulaciones p inner join estudiante e on p.estudiante_id = e.estudiante_id inner join ofertaslaborales ol on p.oferta_id = ol.oferta_id where e.cedula = :cedula and ol.oferta_id = :oferta_id)THEN 1 ELSE 0 END;", nativeQuery = true)
    public Integer thereIsAnApplicationFromThisStudentToThisOffer(String cedula, long oferta_id);


    @Query(value = "select * from postulaciones p inner join estudiante e on p.estudiante_id = e.estudiante_id inner join usuario u on e.usuario_id = u.usuario_id where e.cedula= :cedula ;", nativeQuery = true)
    public List<Postulacion> finByPostulacionEst_id(String cedula);


    /*@Query(value = "select * from postulaciones where estudiante_id =  :estudiante_id;")
    public  List<Postulacion> findByEstudiante(long id);*/

}


