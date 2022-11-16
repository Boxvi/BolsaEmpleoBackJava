package ec.edu.ista.springgc1.controller;

import ec.edu.ista.springgc1.exception.AppException;
import ec.edu.ista.springgc1.model.dto.ContactoEmpresaDTO;
import ec.edu.ista.springgc1.model.entity.ContactoEmpresa;
import ec.edu.ista.springgc1.model.entity.Estudiante;
import ec.edu.ista.springgc1.service.impl.ContactoEmpresaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/contactosEmpresa")
public class ContactoEmpresaController {

    @Autowired
    private ContactoEmpresaServiceImpl contactoEmpresaService;

    @GetMapping
    ResponseEntity<List<?>> list() {
        return ResponseEntity.ok(contactoEmpresaService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(contactoEmpresaService.findById(id));
    }

    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody ContactoEmpresaDTO contactoEmpresaDTO) {

        if (contactoEmpresaService.findByNombre(contactoEmpresaDTO.getNombre()).isPresent()){
            throw new AppException(HttpStatus.BAD_REQUEST, "Contacto ya registrado");
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(contactoEmpresaService.save(contactoEmpresaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ContactoEmpresaDTO contactoEmpresaDTO) {
        ContactoEmpresaDTO contactoFromDb = contactoEmpresaService.findByIdToDTO(id);
        if (!contactoFromDb.getNombre().equalsIgnoreCase(contactoEmpresaDTO.getNombre()) && contactoEmpresaService.findByNombre(contactoEmpresaDTO.getNombre()).isPresent()){
            throw new AppException(HttpStatus.BAD_REQUEST, "Contacto ya registrado");
        }

        contactoFromDb.setEmpresa_id(contactoEmpresaDTO.getEmpresa_id());
        contactoFromDb.setNombre(contactoEmpresaDTO.getNombre());
        contactoFromDb.setCargo(contactoEmpresaDTO.getCargo());
        contactoFromDb.setTelefono(contactoEmpresaDTO.getTelefono());
        contactoFromDb.setEmail(contactoEmpresaDTO.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(contactoEmpresaService.save(contactoFromDb));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ContactoEmpresa contactoFromDb = contactoEmpresaService.findById(id);
        contactoEmpresaService.delete(contactoFromDb.getId());
        return ResponseEntity.noContent().build();
    }

}
