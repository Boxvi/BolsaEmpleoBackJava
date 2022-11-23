package ec.edu.ista.springgc1.service.impl;

import ec.edu.ista.springgc1.exception.ResourceNotFoundException;

import ec.edu.ista.springgc1.model.dto.ReferenciaProfesionalDTO;
import ec.edu.ista.springgc1.model.entity.Estudiante;
import ec.edu.ista.springgc1.model.entity.ReferenciaProfesional;
import ec.edu.ista.springgc1.repository.EstudianteRepository;
import ec.edu.ista.springgc1.repository.ReferenciaProfesionalRepository;
import ec.edu.ista.springgc1.service.generic.impl.GenericServiceImpl;
import ec.edu.ista.springgc1.service.map.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReferenciaProfesionalServiceImpl extends GenericServiceImpl<ReferenciaProfesional> implements Mapper<ReferenciaProfesional, ReferenciaProfesionalDTO> {

    @Autowired
    private ReferenciaProfesionalRepository referenciaProfesionalRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public ReferenciaProfesional mapToEntity(ReferenciaProfesionalDTO referenciaProfesionalDTO) {

        ReferenciaProfesional referenciaProfesional = new ReferenciaProfesional();
        Estudiante estudiante = estudianteRepository.findByCedula(referenciaProfesionalDTO.getCedula())
                .orElseThrow(()-> new ResourceNotFoundException("cedula", referenciaProfesionalDTO.getCedula()));

        referenciaProfesional.setId(referenciaProfesionalDTO.getId());
        referenciaProfesional.setInstitucion(referenciaProfesionalDTO.getInstitucion());
        referenciaProfesional.setNombre(referenciaProfesionalDTO.getNombre());
        referenciaProfesional.setTelefono(referenciaProfesionalDTO.getTelefono());
        referenciaProfesional.setEmail(referenciaProfesionalDTO.getEmail());
        referenciaProfesional.setEstudiante(estudiante);

        return referenciaProfesional;
    }

    @Override
    public ReferenciaProfesionalDTO mapToDTO(ReferenciaProfesional referenciaProfesional) {

        ReferenciaProfesionalDTO referenciaProfesionalDTO = new ReferenciaProfesionalDTO();
        referenciaProfesionalDTO.setId(referenciaProfesional.getId());
        referenciaProfesionalDTO.setInstitucion(referenciaProfesional.getInstitucion());
        referenciaProfesionalDTO.setNombre(referenciaProfesional.getNombre());
        referenciaProfesionalDTO.setTelefono(referenciaProfesional.getTelefono());
        referenciaProfesionalDTO.setEmail(referenciaProfesional.getEmail());
        referenciaProfesionalDTO.setCedula(referenciaProfesional.getEstudiante().getCedula());

        return referenciaProfesionalDTO;
    }


    @Override
    public List findAll() {
        return referenciaProfesionalRepository.findAll()
                .stream()
                .map(c -> mapToDTO(c))
                .collect(Collectors.toList());
    }

    @Override
    public ReferenciaProfesional save(Object entity) {
        return referenciaProfesionalRepository.save(mapToEntity((ReferenciaProfesionalDTO)  entity));
    }

    public ReferenciaProfesionalDTO findByIdToDTO(Long id){
        return mapToDTO(referenciaProfesionalRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("id", id)));
    }

    public List findByEstudiante(long estudiante_id){
        return referenciaProfesionalRepository.findByEstudiante(estudiante_id)
                .stream()
                .map(e -> mapToDTO(e))
                .collect(Collectors.toList());
    }



    public Optional<ReferenciaProfesional> findByInstitucion(String institucion){ return referenciaProfesionalRepository.findByInstitucion(institucion);}

}
