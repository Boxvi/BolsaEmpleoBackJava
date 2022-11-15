package ec.edu.ista.springgc1.repository.generic;

import ec.edu.ista.springgc1.model.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends GenericRepository<Usuario> {

    public Optional<Usuario> findByUsername(String username);
    public Boolean existsByUsername(String username);
}
