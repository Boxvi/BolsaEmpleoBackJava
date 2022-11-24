package ec.edu.ista.springgc1.controller;


import ec.edu.ista.springgc1.model.dto.PerfilOcupacionalDTO;
import ec.edu.ista.springgc1.model.entity.PerfilOcupacional;
import ec.edu.ista.springgc1.service.impl.PerfilOcupacionalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/perfiles")
public class PerfilOcupacionalController {

    @Autowired
    private PerfilOcupacionalServiceImpl ocupacionalService;

    @GetMapping
    ResponseEntity<List<?>> list() {
        return ResponseEntity.ok(ocupacionalService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ocupacionalService.findById(id));
    }

    @GetMapping("/resumen/{id}")
    ResponseEntity<?> findByIdResumen(@PathVariable Long id) {
        return ResponseEntity.ok(ocupacionalService.findByIdToDTO(id));
    }
    @GetMapping("/estudiante/{id}")
    ResponseEntity<?> findByEstudianteId(@PathVariable Long id){
        return ResponseEntity.ok(ocupacionalService.finByEstudiante(id));
    }


    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody PerfilOcupacionalDTO perfilOcupacionalDTO) {
       /* if (ocupacionalService.findByNombre(ciudadDTO.getNombre()).isPresent()){
            throw new AppException(HttpStatus.BAD_REQUEST,"Ya se encuentra registrado la Ciudad");
        }
*/
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ocupacionalService.save(perfilOcupacionalDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody PerfilOcupacionalDTO perfilOcupacionalDTO) {
        PerfilOcupacionalDTO perfilFromDb = ocupacionalService.findByIdToDTO(id);
       /* if (!ciudadDTO.getNombre().equalsIgnoreCase(ciudadFromDb.getNombre()) && ocupacionalService.findByNombre(ciudadDTO.getNombre()).isPresent()){
            throw new AppException(HttpStatus.BAD_REQUEST,"Ya se encuentra registrado la Ciudad");
        }*/
        perfilFromDb.setHabilidades(perfilOcupacionalDTO.getHabilidades());
        perfilFromDb.setActitudes(perfilOcupacionalDTO.getActitudes());
        perfilFromDb.setDestrezas(perfilOcupacionalDTO.getDestrezas());
        perfilFromDb.setCedula(perfilOcupacionalDTO.getCedula());


        return ResponseEntity.status(HttpStatus.CREATED).body(ocupacionalService.save(perfilFromDb));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        PerfilOcupacional perfilFromDb = ocupacionalService.findById(id);
        ocupacionalService.delete(perfilFromDb.getId());
        return ResponseEntity.noContent().build();
    }

}

