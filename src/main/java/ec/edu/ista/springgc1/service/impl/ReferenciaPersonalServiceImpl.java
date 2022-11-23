package ec.edu.ista.springgc1.service.impl;

import ec.edu.ista.springgc1.exception.ResourceNotFoundException;
import ec.edu.ista.springgc1.model.dto.EstudianteDTO;
import ec.edu.ista.springgc1.model.dto.ReferenciaPersonalDTO;
import ec.edu.ista.springgc1.model.entity.Estudiante;
import ec.edu.ista.springgc1.model.entity.ReferenciaPersonal;
import ec.edu.ista.springgc1.repository.EstudianteRepository;
import ec.edu.ista.springgc1.repository.ReferenciaPersonalRepository;
import ec.edu.ista.springgc1.service.generic.impl.GenericServiceImpl;
import ec.edu.ista.springgc1.service.map.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReferenciaPersonalServiceImpl extends GenericServiceImpl<ReferenciaPersonal> implements Mapper<ReferenciaPersonal, ReferenciaPersonalDTO> {

    @Autowired
    private ReferenciaPersonalRepository referenciaPersonalRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;




    @Override
    public ReferenciaPersonal mapToEntity(ReferenciaPersonalDTO referenciaPersonalDTO) {

        ReferenciaPersonal referenciaPersonal = new ReferenciaPersonal();
        Estudiante estudiante = estudianteRepository.findByCedula(referenciaPersonalDTO.getCedula())
                .orElseThrow(()-> new ResourceNotFoundException("cedula", referenciaPersonalDTO.getCedula()));

        referenciaPersonal.setId(referenciaPersonalDTO.getId());
        referenciaPersonal.setNombre(referenciaPersonalDTO.getNombre());
        referenciaPersonal.setTelefono(referenciaPersonalDTO.getTelefono());
        referenciaPersonal.setEstudiante(estudiante);
        return referenciaPersonal;
    }

    @Override
    public ReferenciaPersonalDTO mapToDTO(ReferenciaPersonal referenciaPersonal) {

        ReferenciaPersonalDTO referenciaPersonalDTO = new ReferenciaPersonalDTO();
        referenciaPersonalDTO.setId(referenciaPersonal.getId());
        referenciaPersonalDTO.setNombre(referenciaPersonal.getNombre());
        referenciaPersonalDTO.setCedula(referenciaPersonal.getEstudiante().getCedula());
        referenciaPersonalDTO.setTelefono(referenciaPersonal.getTelefono());

        return referenciaPersonalDTO;
    }

    @Override
    public List findAll() {
        return referenciaPersonalRepository.findAll()
                .stream()
                .map(c -> mapToDTO(c))
                .collect(Collectors.toList());
    }

    public ReferenciaPersonalDTO findByIdToDTO(Long id){
        return mapToDTO(referenciaPersonalRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("id", id)));
    }

    @Override
    public ReferenciaPersonal save(Object entity) {
        return referenciaPersonalRepository.save(mapToEntity((ReferenciaPersonalDTO)  entity));
    }


    public ReferenciaPersonalDTO findByIdToDTO(long id) {
        return mapToDTO(referenciaPersonalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id", id)));}


    public Optional<ReferenciaPersonal> findByNombre(String nombre){ return referenciaPersonalRepository.findByNombre(nombre);}


}
