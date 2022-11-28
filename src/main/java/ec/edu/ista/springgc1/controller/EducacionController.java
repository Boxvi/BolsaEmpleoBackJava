package ec.edu.ista.springgc1.controller;


import ec.edu.ista.springgc1.exception.AppException;
import ec.edu.ista.springgc1.model.dto.EducacionDTO;
import ec.edu.ista.springgc1.model.entity.Educacion;
import ec.edu.ista.springgc1.service.impl.EducacionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/educaciones")
public class EducacionController {

    @Autowired
    private EducacionServiceImpl educacionService;

    @GetMapping
    ResponseEntity<List<?>> list() {
        return ResponseEntity.ok(educacionService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(educacionService.findById(id));
    }

    @GetMapping("/resumen/{id}")
    ResponseEntity<?> findByIdResumen(@PathVariable Long id) {
        return ResponseEntity.ok(educacionService.findByIdToDTO(id));
    }
    @GetMapping("/estudiante/{id}")
    ResponseEntity<?> findByEstudianteId(@PathVariable Long id) {
        return ResponseEntity.ok(educacionService.findByEstudiante(id));
    }

    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody EducacionDTO educacionDTO ) {
        /*if (ciudadService.findByNombre(ciudadDTO.getNombre()).isPresent()){
            throw new AppException(HttpStatus.BAD_REQUEST,"Ya se encuentra registrado la Ciudad");
        }*/

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(educacionService.save(educacionDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody EducacionDTO educacionDTO) {
        EducacionDTO eduFromDb = educacionService.findByIdToDTO(id);
        if (!educacionDTO.getTitulo().equalsIgnoreCase(eduFromDb.getTitulo()) && educacionService.findByTitulo(educacionDTO.getTitulo()).isPresent()){
            throw new AppException(HttpStatus.BAD_REQUEST,"Ya se encuentra registrado este nivel educativo");
        }
        eduFromDb.setAnio(educacionDTO.getAnio());
        eduFromDb.setTitulo(educacionDTO.getTitulo());
        eduFromDb.setCedula(educacionDTO.getCedula());
        eduFromDb.setArea_estudio(educacionDTO.getArea_estudio());
        eduFromDb.setInstitucion_educativa(educacionDTO.getInstitucion_educativa());
        eduFromDb.setNivel(educacionDTO.getNivel());


        return ResponseEntity.status(HttpStatus.CREATED).body(educacionService.save(eduFromDb));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Educacion eduFromDb = educacionService.findById(id);
        educacionService.delete(eduFromDb.getId());
        return ResponseEntity.noContent().build();
    }


}
