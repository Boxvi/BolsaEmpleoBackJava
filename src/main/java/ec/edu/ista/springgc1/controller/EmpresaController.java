package ec.edu.ista.springgc1.controller;

import ec.edu.ista.springgc1.exception.AppException;
import ec.edu.ista.springgc1.model.dto.EmpresaDTO;
import ec.edu.ista.springgc1.model.entity.Empresa;
import ec.edu.ista.springgc1.service.impl.EmpresaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaServiceImpl empresaServiceImpl;

    @GetMapping
    ResponseEntity<List<?>> list() {
        return ResponseEntity.ok(empresaServiceImpl.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(empresaServiceImpl.findById(id));
    }

    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody EmpresaDTO empresaDTO) {

        if (empresaServiceImpl.existsByRuc(empresaDTO.getRuc())){
            throw new AppException(HttpStatus.BAD_REQUEST, "RUC ya se encuentra en otro registro");
        }
        
        if (empresaServiceImpl.existsByNombre(empresaDTO.getNombre())){
            throw new AppException(HttpStatus.BAD_REQUEST, "Empresa ya registrada");
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(empresaServiceImpl.save(empresaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody EmpresaDTO empresaDTO) {
        EmpresaDTO empresaFromDb = empresaServiceImpl.findByIdToDTO(id);
        if (!empresaFromDb.getRuc().equalsIgnoreCase(empresaDTO.getRuc()) && empresaServiceImpl.existsByRuc(empresaDTO.getRuc())){
            throw new AppException(HttpStatus.BAD_REQUEST, "RUC ya se encuentra en otro registro");
        }

        if (!empresaFromDb.getNombre().equalsIgnoreCase(empresaDTO.getNombre()) && empresaServiceImpl.existsByNombre(empresaDTO.getNombre())){
            throw new AppException(HttpStatus.BAD_REQUEST, "Empresa ya registrada");
        }

        empresaFromDb.setUsername(empresaDTO.getUsername());
        empresaFromDb.setSectorEmpresarial(empresaDTO.getSectorEmpresarial());
        empresaFromDb.setRuc(empresaDTO.getRuc());
        empresaFromDb.setNombre(empresaDTO.getNombre());
        empresaFromDb.setTipoEmpresa(empresaDTO.getTipoEmpresa());
        empresaFromDb.setRazonSocial(empresaDTO.getRazonSocial());
        empresaFromDb.setDepartamento(empresaDTO.getDepartamento());
        empresaFromDb.setCiudad(empresaDTO.getCiudad());
        empresaFromDb.setDireccion(empresaDTO.getDireccion());
        empresaFromDb.setSitioWeb(empresaDTO.getSitioWeb());

        return ResponseEntity.status(HttpStatus.CREATED).body(empresaServiceImpl.save(empresaFromDb));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Empresa empresaFromDb = empresaServiceImpl.findById(id);
        empresaServiceImpl.delete(empresaFromDb.getId());
        return ResponseEntity.noContent().build();
    }
}
