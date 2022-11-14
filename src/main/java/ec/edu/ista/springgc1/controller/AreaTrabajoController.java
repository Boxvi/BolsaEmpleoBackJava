package ec.edu.ista.springgc1.controller;

import ec.edu.ista.springgc1.model.entity.AreaTrabajo;
import ec.edu.ista.springgc1.model.entity.Rol;
import ec.edu.ista.springgc1.service.impl.AreaTrabajoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/AreasTrabajo")
public class AreaTrabajoController {

    @Autowired
    private AreaTrabajoServiceImpl areaTrabajoService;

    @GetMapping
    ResponseEntity<List<?>> list() {
        return ResponseEntity.ok(areaTrabajoService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(areaTrabajoService.findById(id));
    }

    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody AreaTrabajo areaTrabajo) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(areaTrabajoService.save(areaTrabajo));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody AreaTrabajo areaTrabajo) {
        AreaTrabajo areaTrabajoFromDb = areaTrabajoService.findById(id);
        areaTrabajoFromDb.setAreaTrabajo_nombre(areaTrabajo.getAreaTrabajo_nombre());

        return ResponseEntity.status(HttpStatus.CREATED).body(areaTrabajoService.save(areaTrabajoFromDb));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        AreaTrabajo areaTrabajoFromDb = areaTrabajoService.findById(id);

        areaTrabajoService.delete(areaTrabajoFromDb.getId());
        return ResponseEntity.noContent().build();
    }

}
