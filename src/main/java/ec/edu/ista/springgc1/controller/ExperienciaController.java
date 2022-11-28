package ec.edu.ista.springgc1.controller;


import ec.edu.ista.springgc1.model.dto.ExperienciaDTO;
import ec.edu.ista.springgc1.model.entity.Experiencia;
import ec.edu.ista.springgc1.service.impl.ExperienciaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/experiencias")
public class ExperienciaController {

    @Autowired
    private ExperienciaServiceImpl experienciaService;

    @GetMapping
    ResponseEntity<List<?>> list() {
        return ResponseEntity.ok(experienciaService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(experienciaService.findByIdToDTO(id));
    }
    @GetMapping("/resumen/{id}")
    ResponseEntity<?> findByIdResumen(@PathVariable Long id) {
        return ResponseEntity.ok(experienciaService.findByIdToDTO(id));
    }
    @GetMapping("/estudiante/{id}")
    ResponseEntity<?> findByEstudianteId(@PathVariable Long id){
        return ResponseEntity.ok(experienciaService.findByEstudiante(id));
    }


    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody ExperienciaDTO experienciaDTO) {
       /* if (experienciaService.findByNombre(experienciaDTO.getNombre()).isPresent()){
            throw new AppException(HttpStatus.BAD_REQUEST,"Ya se encuentra registrado la Ciudad");
        }*/


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(experienciaService.save(experienciaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ExperienciaDTO experienciaDTO) {
        ExperienciaDTO experienciaFromDb = experienciaService.findByIdToDTO(id);
      /* if (!experienciaDTO.getNombre().equalsIgnoreCase(ciudadFromDb.getNombre()) && experienciaService.findByNombre(ciudadDTO.getNombre()).isPresent()){
            throw new AppException(HttpStatus.BAD_REQUEST,"Ya se encuentra registrado la Ciudad");
        }*/

        experienciaFromDb.setInstitucion(experienciaDTO.getInstitucion());
        experienciaFromDb.setCargo(experienciaDTO.getCargo());
        experienciaFromDb.setDuracion(experienciaDTO.getDuracion());
        experienciaFromDb.setActividad(experienciaDTO.getDuracion());
        experienciaFromDb.setCedula(experienciaDTO.getCedula());
        experienciaFromDb.setArea_trabajo(experienciaDTO.getArea_trabajo());


        return ResponseEntity.status(HttpStatus.CREATED).body(experienciaService.save(experienciaFromDb));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Experiencia ciudadFromDb = experienciaService.findById(id);
        experienciaService.delete(ciudadFromDb.getId());
        return ResponseEntity.noContent().build();
    }






}

