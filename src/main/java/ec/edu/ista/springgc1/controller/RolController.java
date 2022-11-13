package ec.edu.ista.springgc1.controller;

import ec.edu.ista.springgc1.model.entity.Rol;
import ec.edu.ista.springgc1.service.impl.RolServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private RolServiceImpl rolService;

    @GetMapping
    ResponseEntity<List<?>> list(){
        return ResponseEntity.ok(rolService.findAll());
    }

    @PostMapping()
    ResponseEntity<?> save(@RequestBody Rol rol){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(rolService.save(rol));
    }

}
