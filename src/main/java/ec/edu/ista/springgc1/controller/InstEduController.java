package ec.edu.ista.springgc1.controller;

import ec.edu.ista.springgc1.model.entity.InstitucionEducativa;
import ec.edu.ista.springgc1.service.impl.InstEduServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/instituciones")
public class InstEduController {
    
    @Autowired
    private InstEduServiceImpl instEduService;

    @GetMapping
    ResponseEntity<List<?>> list() {
        return ResponseEntity.ok(instEduService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(instEduService.findById(id));
    }

    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody InstitucionEducativa institucionEducativa) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(instEduService.save(institucionEducativa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody InstitucionEducativa institucionEducativa) {
        InstitucionEducativa instEduFromDb = instEduService.findById(id);
        instEduFromDb.setNombre(institucionEducativa.getNombre());

        return ResponseEntity.status(HttpStatus.CREATED).body(instEduService.save(instEduFromDb));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        InstitucionEducativa instEduFromDb = instEduService.findById(id);
        instEduService.delete(instEduFromDb.getId());
        return ResponseEntity.noContent().build();
    }
    
}
