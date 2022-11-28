package ec.edu.ista.springgc1.controller;

import ec.edu.ista.springgc1.exception.AppException;
import ec.edu.ista.springgc1.model.dto.PostulacionDTO;
import ec.edu.ista.springgc1.model.entity.Postulacion;
import ec.edu.ista.springgc1.service.impl.EstudianteServiceImpl;
import ec.edu.ista.springgc1.service.impl.PostulacionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.rmi.server.ServerCloneException;
import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/postulaciones")
public class PostulacionController {

    @Autowired
    private PostulacionServiceImpl postulacionService;

    @GetMapping
    ResponseEntity<List<?>> list() {
        return ResponseEntity.ok(postulacionService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(postulacionService.findById(id));
    }

    @GetMapping("/estudiante/{id}")
    ResponseEntity<?> findByEstudianteId(@PathVariable Long id) {

        return ResponseEntity.ok(postulacionService.findByEstudianteId(id));
    }

    @GetMapping("/total")
    ResponseEntity<?> countPosutlaciones() {

        return ResponseEntity.ok(Collections.singletonMap("total", postulacionService.count()));
    }

    @GetMapping("/empresa/{id}")
    ResponseEntity<?> findByEmpresaId(@PathVariable Long id) {

        return ResponseEntity.ok(postulacionService.findByEmpresaId(id));
    }

    @GetMapping("/resumen/{id}")
    ResponseEntity<?> findByIdResumen(@PathVariable Long id) {
        return ResponseEntity.ok(postulacionService.findByIdToDTO(id));
    }

    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody PostulacionDTO postulacionDTO) {
        if (postulacionService.thereIsAnApplicationFromThisStudentToThisOffer(postulacionDTO.getCedula(), postulacionDTO.getOfertalaboral_id()) != 0) {
            throw new AppException(HttpStatus.BAD_REQUEST, "El estudiante ya está aplicando a esta oferta");
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postulacionService.save(postulacionDTO));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody PostulacionDTO postulacionDTO) {

        PostulacionDTO postulacionFromDb = postulacionService.findByIdToDTO(id);

        if (!postulacionFromDb.getCedula().equals(postulacionDTO.getCedula()) && postulacionService.thereIsAnApplicationFromThisStudentToThisOffer(postulacionDTO.getCedula(), postulacionDTO.getOfertalaboral_id()) != 0) {
            throw new AppException(HttpStatus.BAD_REQUEST, "El estudiante ya está aplicando a esta oferta");
        }
        postulacionFromDb.setFecha(postulacionDTO.getFecha());
        postulacionFromDb.setEstado(postulacionDTO.getEstado());
        postulacionFromDb.setOfertalaboral_id(postulacionDTO.getOfertalaboral_id());
        postulacionFromDb.setCedula(postulacionDTO.getCedula());


        return ResponseEntity.status(HttpStatus.CREATED).body(postulacionService.save(postulacionFromDb));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Postulacion postulacion = postulacionService.findById(id);

        postulacionService.delete(postulacion.getId());
        return ResponseEntity.noContent().build();
    }


    @GetMapping("by_est/{cedula}")
    public ResponseEntity<?> findByEstudianteCedula(@PathVariable String cedula) {

        return ResponseEntity.ok(postulacionService.finByPostulacionEstCedula(cedula));
    }


}

