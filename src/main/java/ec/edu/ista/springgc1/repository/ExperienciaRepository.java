package ec.edu.ista.springgc1.repository;


import ec.edu.ista.springgc1.repository.generic.GenericRepository;
import ec.edu.ista.springgc1.model.entity.Experiencia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienciaRepository extends GenericRepository<Experiencia> {

    @Query(value = "select * from experiencias where estudiante_id = :estudiante_id ;",nativeQuery = true)
    public List<Experiencia> findByEstudiante(long estudiante_id);


}