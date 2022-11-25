package ec.edu.ista.springgc1.controller;

import ec.edu.ista.springgc1.exception.AppException;
import ec.edu.ista.springgc1.model.dto.EstudianteDTO;
import ec.edu.ista.springgc1.model.entity.Estudiante;
import ec.edu.ista.springgc1.service.impl.EstudianteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteServiceImpl estudianteService;

    @GetMapping
    ResponseEntity<List<?>> list() {
        return ResponseEntity.ok(estudianteService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(estudianteService.findById(id));
    }

    @GetMapping("/resumen/{id}")
    ResponseEntity<?> findByIdResumen(@PathVariable Long id) {
        return ResponseEntity.ok(estudianteService.findByIdToDTO(id));
    }

    @GetMapping("/cedula/{cedula}")
    ResponseEntity<?> findByCedulaResumen(@PathVariable String cedula) {
        return ResponseEntity.ok(estudianteService.findByCedulaToDTO(cedula));
    }

    @GetMapping("/usuario/{id}")
    ResponseEntity<?> findByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(estudianteService.findByUsuario(id));
    }

    @GetMapping("/total")
    ResponseEntity<?> countEstudiantes() {
        return ResponseEntity.ok(Collections.singletonMap("total", estudianteService.count()));
    }
    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody EstudianteDTO estudianteDTO) {

        if (estudianteService.existsByCedula(estudianteDTO.getCedula())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Cédula ya se encuentra en otro registro");
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(estudianteService.save(estudianteDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody EstudianteDTO estudianteDTO) {
        EstudianteDTO estudianteFromDb = estudianteService.findByIdToDTO(id);
        if (!estudianteFromDb.getCedula().equalsIgnoreCase(estudianteDTO.getCedula()) && estudianteService.existsByCedula(estudianteDTO.getCedula())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Cédula ya se encuentra en otro registro");
        }
        estudianteFromDb.setUsername(estudianteDTO.getUsername());
        estudianteFromDb.setCedula(estudianteDTO.getCedula());
        estudianteFromDb.setNombres(estudianteDTO.getNombres());
        estudianteFromDb.setApellidos(estudianteDTO.getApellidos());
        estudianteFromDb.setGenero(estudianteDTO.getGenero());
        estudianteFromDb.setFechaNacimiento(estudianteDTO.getFechaNacimiento());
        estudianteFromDb.setCiudad(estudianteDTO.getCiudad());
        estudianteFromDb.setDireccion(estudianteDTO.getDireccion());
        estudianteFromDb.setEstadoCivil(estudianteDTO.getEstadoCivil());
        estudianteFromDb.setRutaImagen(estudianteDTO.getRutaImagen());
        estudianteFromDb.setUrlImagen(estudianteDTO.getUrlImagen());

        return ResponseEntity.status(HttpStatus.CREATED).body(estudianteService.save(estudianteFromDb));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Estudiante estudianteFromDb = estudianteService.findById(id);
        estudianteService.delete(estudianteFromDb.getId());
        return ResponseEntity.noContent().build();
    }
}
