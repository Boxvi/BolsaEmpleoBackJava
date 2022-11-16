package ec.edu.ista.springgc1.controller;


import ec.edu.ista.springgc1.exception.AppException;
import ec.edu.ista.springgc1.model.dto.ReferenciaProfesionalDTO;
import ec.edu.ista.springgc1.model.entity.ReferenciaProfesional;
import ec.edu.ista.springgc1.service.impl.ReferenciaProfesionalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/referenciaProfesional")
public class ReferenciaProfesionalController {

    @Autowired
    private ReferenciaProfesionalServiceImpl referenciaProfesionalService;

    @GetMapping
    ResponseEntity<List<?>> list() {
        return ResponseEntity.ok(referenciaProfesionalService.findAll());}



    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(referenciaProfesionalService.findById(id));}


    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody ReferenciaProfesionalDTO referenciaProfesionalDTO){

        if (referenciaProfesionalService.findByInstitucion(referenciaProfesionalDTO.getInstitucion()).isPresent()){
            throw new AppException(HttpStatus.BAD_REQUEST,"el dato ingresado ya fue registrado");
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(referenciaProfesionalService.save(referenciaProfesionalDTO));

    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ReferenciaProfesionalDTO referenciaProfesionalDTO){

        ReferenciaProfesionalDTO referenciaProfesionalFromDb = referenciaProfesionalService.findByIdToDTO(id);

        if (!referenciaProfesionalDTO.getInstitucion().equalsIgnoreCase(referenciaProfesionalFromDb.getInstitucion()) && referenciaProfesionalService.findByInstitucion(referenciaProfesionalDTO.getInstitucion()).isPresent() ){
            throw new AppException(HttpStatus.BAD_REQUEST,"el dato ingresado ya fue registrado");
        }

        referenciaProfesionalFromDb.setInstitucion(referenciaProfesionalDTO.getInstitucion());
        referenciaProfesionalFromDb.setNombre(referenciaProfesionalDTO.getNombre());
        referenciaProfesionalFromDb.setTelefono(referenciaProfesionalDTO.getTelefono());
        referenciaProfesionalFromDb.setEmail(referenciaProfesionalDTO.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(referenciaProfesionalService.save(referenciaProfesionalFromDb));

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        ReferenciaProfesional referenciaProfesionalFromDb = referenciaProfesionalService.findById(id);

        referenciaProfesionalService.delete(referenciaProfesionalFromDb.getId());

        return ResponseEntity.noContent().build();
    }

}
