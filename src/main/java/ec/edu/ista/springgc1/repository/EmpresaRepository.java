package ec.edu.ista.springgc1.repository;

import ec.edu.ista.springgc1.model.entity.Empresa;
import ec.edu.ista.springgc1.repository.generic.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends GenericRepository<Empresa> {

    Boolean existsByNombre(String nombre);

    Boolean existsByRuc(String ruc);

    Optional<Empresa> findByNombre(String nombre);

}
