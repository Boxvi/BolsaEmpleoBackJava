package ec.edu.ista.springgc1.service.impl;


import ec.edu.ista.springgc1.exception.ResourceNotFoundException;
import ec.edu.ista.springgc1.model.dto.PerfilOcupacionalDTO;
import ec.edu.ista.springgc1.model.entity.Estudiante;
import ec.edu.ista.springgc1.model.entity.PerfilOcupacional;
import ec.edu.ista.springgc1.repository.EstudianteRepository;
import ec.edu.ista.springgc1.repository.PerfilOcupacionalRepository;
import ec.edu.ista.springgc1.service.generic.impl.GenericServiceImpl;
import ec.edu.ista.springgc1.service.map.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PerfilOcupacionalServiceImpl extends GenericServiceImpl<PerfilOcupacional> implements Mapper<PerfilOcupacional, PerfilOcupacionalDTO> {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private PerfilOcupacionalRepository perfilOcupacionalRepository;

    @Override
    public PerfilOcupacional mapToEntity(PerfilOcupacionalDTO perfilOcupacionalDTO) {
        PerfilOcupacional perfilOcupacional = new PerfilOcupacional();

        Estudiante estudiante = estudianteRepository.findByCedula(perfilOcupacionalDTO.getCedula())
                .orElseThrow(()-> new ResourceNotFoundException("cedula",perfilOcupacionalDTO.getCedula() ));

        perfilOcupacional.setId(perfilOcupacionalDTO.getId());
        perfilOcupacional.setHabilidades(perfilOcupacionalDTO.getHabilidades());
        perfilOcupacional.setDestrezas(perfilOcupacionalDTO.getDestrezas());
        perfilOcupacional.setActitudes(perfilOcupacionalDTO.getActitudes());
        perfilOcupacional.setEstudiante(estudiante);

        return perfilOcupacional;
    }

    @Override
    public PerfilOcupacionalDTO mapToDTO(PerfilOcupacional perfilOcupacional) {

        PerfilOcupacionalDTO perfilOcupacionalDTO = new PerfilOcupacionalDTO();
        perfilOcupacionalDTO.setId(perfilOcupacional.getId());
        perfilOcupacionalDTO.setHabilidades(perfilOcupacional.getHabilidades());
        perfilOcupacionalDTO.setDestrezas(perfilOcupacional.getDestrezas());
        perfilOcupacionalDTO.setActitudes(perfilOcupacional.getActitudes());
        perfilOcupacionalDTO.setCedula(perfilOcupacional.getEstudiante().getCedula());

        return perfilOcupacionalDTO;
    }

    @Override
    public List findAll() {
        return perfilOcupacionalRepository.findAll()
                .stream()
                .map(c -> mapToDTO(c))
                .collect(Collectors.toList());
    }

    @Override
    public PerfilOcupacional save(Object entity) {
        return perfilOcupacionalRepository.save(mapToEntity((PerfilOcupacionalDTO) entity));
    }

   public PerfilOcupacionalDTO finByEstudiante(long estudiante_id){

        return mapToDTO(perfilOcupacionalRepository.findByEstudiante(estudiante_id).orElseThrow(() -> new ResourceNotFoundException("estudiante_id", estudiante_id)));
    }




    public PerfilOcupacionalDTO findByIdToDTO(long id) {
        return mapToDTO(perfilOcupacionalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id", id)));
    }




}

