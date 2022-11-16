package ec.edu.ista.springgc1.controller;

import ec.edu.ista.springgc1.model.dto.PostulacionDTO;
import ec.edu.ista.springgc1.model.entity.Postulacion;
import ec.edu.ista.springgc1.service.impl.EstudianteServiceImpl;
import ec.edu.ista.springgc1.service.impl.PostulacionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/postulaciones")
public class PostulacionController {

    @Autowired
    private PostulacionServiceImpl postulacionService;
    @Autowired
    private EstudianteServiceImpl estudianteService;


    @GetMapping
    ResponseEntity<List<?>> list() {
        return ResponseEntity.ok(postulacionService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(postulacionService.findById(id));
    }


    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody PostulacionDTO postulacionDTO) {
      /*  if ( estudianteService.findByCedula(postulacionDTO.getCedula()).isPresent()){
            throw new AppException(HttpStatus.BAD_REQUEST,"Ya te has postulado a esta oferta");
        }*/
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postulacionService.save(postulacionDTO));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody PostulacionDTO postulacionDTO) {
        PostulacionDTO postulacionFromDb = postulacionService.findByIdToDTO(id);
       /* if (estudianteService.existsByCedula(postulacionFromDb.getCedula())){
            throw new AppException(HttpStatus.BAD_REQUEST,"Ya te has postulado a esta oferta");
        }*/

        postulacionFromDb.setFecha(postulacionDTO.getFecha());
        postulacionFromDb.setEstado(postulacionDTO.getEstado());
        postulacionFromDb.setCargo(postulacionDTO.getCargo());
        postulacionFromDb.setCedula(postulacionDTO.getCedula());




        return ResponseEntity.status(HttpStatus.CREATED).body(postulacionService.save(postulacionFromDb));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Postulacion postulacion= postulacionService.findById(id);

        postulacionService.delete(postulacion.getId());
        return ResponseEntity.noContent().build();
    }


}

