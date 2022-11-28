package ec.edu.ista.springgc1.controller;


import ec.edu.ista.springgc1.model.dto.OfertaLaboralDTO;
import ec.edu.ista.springgc1.model.entity.OfertaLaboral;
import ec.edu.ista.springgc1.service.impl.OfertaLaboralServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/ofertas")
public class OfertaLaboralController {

    @Autowired
    private OfertaLaboralServiceImpl ofertaLaboralService;

    @GetMapping
    ResponseEntity<List<?>> list() {
        return ResponseEntity.ok(ofertaLaboralService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ofertaLaboralService.findById(id));
    }

    @GetMapping("/resumen/{id}")
    ResponseEntity<?> findByIdResumen(@PathVariable Long id) {
        return ResponseEntity.ok(ofertaLaboralService.findByIdToDTO(id));
    }

    @GetMapping("/empresa/{id}")
    ResponseEntity<?> findByEmpresaId(@PathVariable Long id) {
        return ResponseEntity.ok(ofertaLaboralService.findByEmpresa(id));
    }

    @GetMapping("/cargo/{cargo}")
    ResponseEntity<?> findByCargo(@PathVariable String cargo) {
        return ResponseEntity.ok(ofertaLaboralService.findByCargoIgnoringCase(cargo));
    }

    @GetMapping("/total")
    ResponseEntity<?> countOfertas() {
        return ResponseEntity.ok(Collections.singletonMap("total", ofertaLaboralService.countOfertas()));
    }

    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody OfertaLaboralDTO ofertaLaboralDTO) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ofertaLaboralService.save(ofertaLaboralDTO));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody OfertaLaboralDTO ofertaLaboralDTO) {
        OfertaLaboralDTO ofertaFromDb = ofertaLaboralService.findByIdToDTO(id);
       /* if (!ciudadDTO.getNombre().equalsIgnoreCase(ciudadFromDb.getNombre()) && ciudadService.findByNombre(ciudadDTO.getNombre()).isPresent()){
            throw new AppException(HttpStatus.BAD_REQUEST,"Ya se encuentra registrado la Ciudad");
        }*/

        ofertaFromDb.setCargo(ofertaLaboralDTO.getCargo());
        ofertaFromDb.setDescripcion(ofertaLaboralDTO.getDescripcion());
        ofertaFromDb.setArea_conocimiento(ofertaLaboralDTO.getArea_conocimiento());
        ofertaFromDb.setSalario(ofertaLaboralDTO.getSalario());
        ofertaFromDb.setJornada(ofertaLaboralDTO.getJornada());
        ofertaFromDb.setRequisitos_academicos(ofertaLaboralDTO.getRequisitos_academicos());
        ofertaFromDb.setExperiencia(ofertaLaboralDTO.getExperiencia());
        ofertaFromDb.setUbicacion(ofertaLaboralDTO.getUbicacion());
        ofertaFromDb.setFecha_inicio(ofertaLaboralDTO.getFecha_inicio());
        ofertaFromDb.setFecha_fin(ofertaLaboralDTO.getFecha_fin());
        ofertaFromDb.setCiudad(ofertaLaboralDTO.getCiudad());
        ofertaFromDb.setEmpresa(ofertaLaboralDTO.getEmpresa());


        return ResponseEntity.status(HttpStatus.CREATED).body(ofertaLaboralService.save(ofertaFromDb));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        OfertaLaboral ofertaLaboral = ofertaLaboralService.findById(id);

        ofertaLaboralService.delete(ofertaLaboral.getId());
        return ResponseEntity.noContent().build();
    }


    @GetMapping("dto/{id}")
    ResponseEntity<?> findByIdDTO(@PathVariable Long id) {
        return ResponseEntity.ok(ofertaLaboralService.findByIdToDTO(id));
    }


}
