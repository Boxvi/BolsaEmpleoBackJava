package ec.edu.ista.springgc1.controller;

import ec.edu.ista.springgc1.model.entity.Provincia;
import ec.edu.ista.springgc1.model.entity.Rol;
import ec.edu.ista.springgc1.service.impl.ProvinciaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/provincias")
public class ProvinciaController {

    @Autowired
    private ProvinciaServiceImpl provinciaService;

    @GetMapping
    ResponseEntity<List<?>> list() {
        return ResponseEntity.ok(provinciaService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(provinciaService.findById(id));
    }

    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody Provincia provincia) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(provinciaService.save(provincia));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Provincia provincia) {
        Provincia provinciaFromDb = provinciaService.findById(id);
        provinciaFromDb.setNombre_provincia(provincia.getNombre_provincia());
        provinciaFromDb.setNombre_pais(provincia.getNombre_pais());

        return ResponseEntity.status(HttpStatus.CREATED).body(provinciaService.save(provinciaFromDb));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Provincia provinciaFromDb = provinciaService.findById(id);
        provinciaService.delete(provinciaFromDb.getId());
        return ResponseEntity.noContent().build();
    }



}
