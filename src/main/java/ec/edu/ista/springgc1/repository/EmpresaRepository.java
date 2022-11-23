package ec.edu.ista.springgc1.repository;

import ec.edu.ista.springgc1.model.entity.Empresa;
import ec.edu.ista.springgc1.model.entity.Estudiante;
import ec.edu.ista.springgc1.repository.generic.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpresaRepository extends GenericRepository<Empresa> {

    Boolean existsByNombre(String nombre);

    Boolean existsByRuc(String ruc);

    @Query(value = "select * from empresa where usuario_id = :usuario_id", nativeQuery = true)
    Optional<Empresa> findByUsuario(long usuario_id);

    Optional<Empresa> findByNombre(String nombre);

}
