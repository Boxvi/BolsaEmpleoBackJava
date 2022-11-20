package ec.edu.ista.springgc1.controller;

import ec.edu.ista.springgc1.exception.AppException;
import ec.edu.ista.springgc1.model.dto.CapacitacionDTO;
import ec.edu.ista.springgc1.model.entity.Capacitacion;
import ec.edu.ista.springgc1.model.entity.Postulacion;
import ec.edu.ista.springgc1.service.impl.CapacitacionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/capacitacion")
public class CapacitacionController {

    @Autowired
    private CapacitacionServiceImpl capacitacionService;

    @GetMapping
    ResponseEntity<List<?>> list() {
        return ResponseEntity.ok(capacitacionService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(capacitacionService.findByIdToDTO(id));
    }

    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody CapacitacionDTO capacitacionDTO ) {

        if (capacitacionService.findByInstitucion(capacitacionDTO.getInstitucion()).isPresent()){
            throw new AppException(HttpStatus.BAD_REQUEST,"La institucion ya se encuentra registrado");
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(capacitacionService.save(capacitacionDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody CapacitacionDTO capacitacionDTO) {
        CapacitacionDTO capacitacionFromDb = capacitacionService.findByIdToDTO(id);

        if (!capacitacionDTO.getInstitucion().equalsIgnoreCase(capacitacionFromDb.getInstitucion()) && capacitacionService.findByInstitucion(capacitacionDTO.getInstitucion()).isPresent()){
            throw new AppException(HttpStatus.BAD_REQUEST,"el dato ingresado ya fue registrado");
        }

        capacitacionFromDb.setInstitucion(capacitacionDTO.getInstitucion());
        capacitacionFromDb.setTipoCapacitacion(capacitacionDTO.getTipoCapacitacion());
        capacitacionFromDb.setTipoCertificado(capacitacionDTO.getTipoCertificado());
        capacitacionFromDb.setFechaInicio(capacitacionDTO.getFechaInicio());
        capacitacionFromDb.setFechaFin(capacitacionDTO.getFechaFin());
        capacitacionFromDb.setNumHoras(capacitacionDTO.getNumHoras());
        capacitacionFromDb.setNombre_capacitacion(capacitacionDTO.getNombre_capacitacion());

        return ResponseEntity.status(HttpStatus.CREATED).body(capacitacionService.save(capacitacionFromDb));


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Capacitacion capacitacionFromDb = capacitacionService.findById(id);
        capacitacionService.delete(capacitacionFromDb.getId());
        return ResponseEntity.noContent().build();
    }


    @GetMapping("by_ced/{cedula}")
    public List<Capacitacion> findByIdEst(@PathVariable String cedula) {

        return capacitacionService.findByCedulaCapacitacions(cedula);
    }



}

