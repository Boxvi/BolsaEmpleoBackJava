package ec.edu.ista.springgc1.controller;

import ec.edu.ista.springgc1.exception.AppException;
import ec.edu.ista.springgc1.model.dto.EstudianteDTO;
import ec.edu.ista.springgc1.model.entity.Estudiante;
import ec.edu.ista.springgc1.service.impl.EstudianteServiceImpl;
import ec.edu.ista.springgc1.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {
    
    @Autowired
    private EstudianteServiceImpl estudianteService;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @GetMapping
    ResponseEntity<List<?>> list() {
        return ResponseEntity.ok(estudianteService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(estudianteService.findById(id));
    }

    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody EstudianteDTO estudianteDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(estudianteService.save(estudianteDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody EstudianteDTO estudianteDTO) {
        EstudianteDTO estudianteFromDb = estudianteService.findByIdToDTO(id);

        estudianteFromDb.setUsername(estudianteDTO.getUsername());
        estudianteFromDb.setCedula(estudianteDTO.getCedula());
        estudianteFromDb.setNombres(estudianteDTO.getNombres());
        estudianteFromDb.setApellidos(estudianteDTO.getApellidos());
        estudianteFromDb.setGenero(estudianteDTO.getGenero());
        estudianteFromDb.setFechaNacimiento(estudianteDTO.getFechaNacimiento());
        estudianteFromDb.setCiudad(estudianteDTO.getCiudad());
        estudianteFromDb.setDireccion(estudianteDTO.getDireccion());
        estudianteFromDb.setEstadoCivil(estudianteDTO.getEstadoCivil());
        estudianteFromDb.setFotografia(estudianteDTO.getFotografia());

        return ResponseEntity.status(HttpStatus.CREATED).body(estudianteService.save(estudianteFromDb));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Estudiante estudianteFromDb = estudianteService.findById(id);
        estudianteService.delete(estudianteFromDb.getId());
        return ResponseEntity.noContent().build();
    }
}
