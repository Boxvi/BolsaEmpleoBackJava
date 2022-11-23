package ec.edu.ista.springgc1.repository;

import ec.edu.ista.springgc1.model.entity.ContactoEmpresa;
import ec.edu.ista.springgc1.repository.generic.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactoEmpresaRepository extends GenericRepository<ContactoEmpresa> {

    Optional<ContactoEmpresa> findByNombre(String nombre);

    @Query(value = "select * from contacto_empresa where emp_id = :empresa_id", nativeQuery = true)
    List<ContactoEmpresa> findByEmpresaId(long empresa_id);
}
