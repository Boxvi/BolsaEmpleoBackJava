package ec.edu.ista.springgc1.repository;

import ec.edu.ista.springgc1.model.entity.InstitucionEducativa;
import ec.edu.ista.springgc1.repository.generic.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstEduRepository extends GenericRepository<InstitucionEducativa> {
}
