package ec.edu.ista.springgc1.controller;

import ec.edu.ista.springgc1.exception.AppException;
import ec.edu.ista.springgc1.model.dto.ReferenciaPersonalDTO;
import ec.edu.ista.springgc1.model.entity.ReferenciaPersonal;
import ec.edu.ista.springgc1.service.impl.EstudianteServiceImpl;
import ec.edu.ista.springgc1.service.impl.ReferenciaPersonalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/referenciaPersonal")
public class ReferenciaPersonalController {

    @Autowired
    private ReferenciaPersonalServiceImpl referenciaPersonalService;

    @GetMapping
    ResponseEntity<List<?>> list() {
        return ResponseEntity.ok(referenciaPersonalService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(referenciaPersonalService.findByIdToDTO(id));
    }

    @GetMapping("/resumen/{id}")
    ResponseEntity<?> findByIdResumen(@PathVariable Long id) {
        return ResponseEntity.ok(referenciaPersonalService.findByIdToDTO(id));
    }

    @GetMapping("/estudiante/{id}")
    ResponseEntity<?> findByEstudianteId(@PathVariable Long id) {
        return ResponseEntity.ok(referenciaPersonalService.findByEstudiante(id));
    }

    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody ReferenciaPersonalDTO referenciaPersonalDTO) {

        if (referenciaPersonalService.findByNombre(referenciaPersonalDTO.getNombre()).isPresent()) {
            throw new AppException(HttpStatus.BAD_REQUEST, "La Referencia Personal que ingreso ya encuentra registrado");
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(referenciaPersonalService.save(referenciaPersonalDTO));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ReferenciaPersonalDTO referenciaPersonalDTO) {

        ReferenciaPersonalDTO referenciaPersonalFromDb = referenciaPersonalService.findByIdToDTO(id);

        if (!referenciaPersonalDTO.getNombre().equalsIgnoreCase(referenciaPersonalFromDb.getNombre()) && referenciaPersonalService.findByNombre(referenciaPersonalDTO.getNombre()).isPresent()) {
            throw new AppException(HttpStatus.BAD_REQUEST, "La referencia Personal ingresado ya fue registrado");
        }

        referenciaPersonalFromDb.setNombre(referenciaPersonalDTO.getNombre());
        referenciaPersonalFromDb.setTelefono(referenciaPersonalDTO.getTelefono());

        return ResponseEntity.status(HttpStatus.CREATED).body(referenciaPersonalService.save(referenciaPersonalFromDb));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        ReferenciaPersonal referenciaPersonalFromDb = referenciaPersonalService.findById(id);

        referenciaPersonalService.delete(referenciaPersonalFromDb.getId());

        return ResponseEntity.noContent().build();

    }
}
