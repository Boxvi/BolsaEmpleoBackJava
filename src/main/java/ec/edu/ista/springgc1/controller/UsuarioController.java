package ec.edu.ista.springgc1.controller;

import ec.edu.ista.springgc1.exception.AppException;
import ec.edu.ista.springgc1.model.dto.UsuarioDTO;
import ec.edu.ista.springgc1.model.entity.Usuario;
import ec.edu.ista.springgc1.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @GetMapping
    public ResponseEntity<List<?>> list() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO usuarioFromDb = usuarioService.findByIdToDTO(id);
        if (!usuarioFromDb.getUsername().equalsIgnoreCase(usuarioDTO.getUsername()) && usuarioService.existsByUsername(usuarioDTO.getUsername())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Username ya se encuentra en otro registro");
        }

        usuarioFromDb.setUsername(usuarioDTO.getUsername());
        usuarioFromDb.setPassword(usuarioDTO.getPassword());
        usuarioFromDb.setEmail(usuarioDTO.getEmail());
        usuarioFromDb.setTelefono(usuarioDTO.getTelefono());
        usuarioFromDb.setEstado(usuarioDTO.isEstado());
        usuarioFromDb.setRol(usuarioDTO.getRol());

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioFromDb));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Usuario estudianteFromDb = usuarioService.findById(id);
        usuarioService.delete(estudianteFromDb.getId());
        return ResponseEntity.noContent().build();
    }


}
