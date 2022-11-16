package ec.edu.ista.springgc1.repository.generic;

import ec.edu.ista.springgc1.model.entity.ContactoEmpresa;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactoEmpresaRepository extends GenericRepository<ContactoEmpresa>{

    Optional<ContactoEmpresa> findByNombre(String nombre);
}
