package ec.edu.ista.springgc1.controller;

import ec.edu.ista.springgc1.exception.AppException;
import ec.edu.ista.springgc1.model.dto.LogroDTO;
import ec.edu.ista.springgc1.model.entity.Logro;
import ec.edu.ista.springgc1.service.impl.EstudianteServiceImpl;
import ec.edu.ista.springgc1.service.impl.LogroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/logro")
public class LogroController {

    @Autowired
    private LogroServiceImpl logroService;

    @Autowired
    private EstudianteServiceImpl estudianteService;

    @GetMapping
    ResponseEntity<List<?>> list() {
        return ResponseEntity.ok(logroService.findAll());}

    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(logroService.findById(id));
    }


    @GetMapping("/estudiante/{id}")
    ResponseEntity<?> findByEstudianteId(@PathVariable Long id) {
        return ResponseEntity.ok(logroService.findByEstudiante(id));
    }

    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody LogroDTO logroDTO) {

        if (logroService.findByTipoLogro(logroDTO.getTipoLogro()).isPresent()){
            throw new AppException(HttpStatus.BAD_REQUEST,"El Tipo de Logro ingresado ya fue registrado");
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(logroService.save(logroDTO));
    }




    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody LogroDTO logroDTO) {

        LogroDTO logroFromDb = logroService.findByIdToDTO(id);
        if (!logroDTO.getTipoLogro().equalsIgnoreCase(logroFromDb.getTipoLogro()) && logroService.findByTipoLogro(logroDTO.getTipoLogro()).isPresent()){
            throw new AppException(HttpStatus.BAD_REQUEST,"el Tipo de Logro ingresado ya fue registrado");
        }

        logroFromDb.setTipoLogro(logroDTO.getTipoLogro());
        logroFromDb.setDescripcion(logroDTO.getDescripcion());

        return ResponseEntity.status(HttpStatus.CREATED).body(logroService.save(logroFromDb));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Logro logroFromDb = logroService.findById(id);

        logroService.delete(logroFromDb.getId());

        return ResponseEntity.noContent().build();
    }
}