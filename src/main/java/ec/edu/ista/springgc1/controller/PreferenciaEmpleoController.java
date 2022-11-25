package ec.edu.ista.springgc1.controller;


import ec.edu.ista.springgc1.exception.AppException;
import ec.edu.ista.springgc1.model.dto.PreferenciaEmpleoDTO;
import ec.edu.ista.springgc1.model.entity.PreferenciaEmpleo;
import ec.edu.ista.springgc1.service.impl.PreferenciaEmpleoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/preferenciasempleo")
public class PreferenciaEmpleoController {
    @Autowired
    private PreferenciaEmpleoServiceImpl empleoService;

    @GetMapping
    ResponseEntity<List<?>> list() {
        return ResponseEntity.ok(empleoService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(empleoService.findById(id));
    }

    @GetMapping("/resumen/{id}")
    ResponseEntity<?> findByIdResumen(@PathVariable Long id) {
        return ResponseEntity.ok(empleoService.findByIdToDTO(id));
    }

    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody PreferenciaEmpleoDTO empleoDTO) {
        if (empleoService.existAlreadyAPreferenciaEmpleoWithThisDNI(empleoDTO.getCedula_estudiante()) != 0) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Ya se encuentra una preferencia de empleo registrada con esta cédula");
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(empleoService.save(empleoDTO));
    }

    @GetMapping("/estudiante/{id}")
    ResponseEntity<?> findByEstudianteId(@PathVariable Long id) {

        return ResponseEntity.ok(empleoService.findByEstudiante(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody PreferenciaEmpleoDTO empleoDTO) {
        //EducacionDTO educacionFronmDb = empleoService.findById(id);
        PreferenciaEmpleoDTO empleoFromDb = empleoService.findByIdToDTO(id);
        if (!empleoFromDb.getCedula_estudiante().equals(empleoDTO.getCedula_estudiante()) && empleoService.existAlreadyAPreferenciaEmpleoWithThisDNI(empleoDTO.getCedula_estudiante()) != 0) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Ya se encuentra una preferencia de empleo registrada con esta cédula");
        }
        empleoFromDb.setSector_empresarial(empleoDTO.getSector_empresarial());
        empleoFromDb.setSalario(empleoDTO.getSalario());
        empleoFromDb.setTiempo(empleoDTO.getTiempo());
        empleoFromDb.setCedula_estudiante(empleoDTO.getCedula_estudiante());


        return ResponseEntity.status(HttpStatus.CREATED).body(empleoService.save(empleoFromDb));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        PreferenciaEmpleo emplpeoFromDb = empleoService.findById(id);
        empleoService.delete(emplpeoFromDb.getId());
        return ResponseEntity.noContent().build();
    }

}

