package ec.edu.ista.springgc1.controller;


import ec.edu.ista.springgc1.exception.AppException;
import ec.edu.ista.springgc1.model.entity.Nivel;
import ec.edu.ista.springgc1.model.entity.Rol;
import ec.edu.ista.springgc1.service.impl.NivelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/niveles")
public class NivelController {

    @Autowired
    private NivelServiceImpl nivelService;

    @GetMapping
    ResponseEntity<List<?>> list() {
        return ResponseEntity.ok(nivelService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok( nivelService.findById(id));
    }


    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody Nivel nivel) {
        if (nivelService.findByNombre(nivel.getNombre()).isPresent()){
            throw new AppException(HttpStatus.BAD_REQUEST,"el dato ingresado  ya se encuentra registrado");
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(nivelService.save(nivel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Nivel nivel) {
        Nivel nivelFromDb = nivelService.findById(id);
        if (!nivel.getNombre().equalsIgnoreCase(nivelFromDb.getNombre()) && nivelService.findByNombre(nivel.getNombre()).isPresent()){
            throw new AppException(HttpStatus.BAD_REQUEST,"el dato ingresado ya se encuentra registrado");
        }
        nivelFromDb.setNombre(nivel.getNombre());

        return ResponseEntity.status(HttpStatus.CREATED).body(nivelService.save(nivelFromDb));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Nivel nivelFromDb = nivelService.findById(id);
        nivelService.delete(nivelFromDb.getId());
        return ResponseEntity.noContent().build();
    }


}
