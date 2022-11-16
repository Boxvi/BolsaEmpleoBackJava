package ec.edu.ista.springgc1.service.impl;


import ec.edu.ista.springgc1.exception.ResourceNotFoundException;

import ec.edu.ista.springgc1.model.dto.ExperienciaDTO;
import ec.edu.ista.springgc1.model.entity.AreaTrabajo;

import ec.edu.ista.springgc1.model.entity.Estudiante;
import ec.edu.ista.springgc1.repository.AreaTrabajoRepository;
import ec.edu.ista.springgc1.repository.EstudianteRepository;
import ec.edu.ista.springgc1.repository.ExperienciaRepository;
import ec.edu.ista.springgc1.model.entity.Experiencia;
import ec.edu.ista.springgc1.service.generic.impl.GenericServiceImpl;
import ec.edu.ista.springgc1.service.map.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExperienciaServiceImpl extends GenericServiceImpl<Experiencia> implements Mapper<Experiencia, ExperienciaDTO> {
    @Autowired
    private ExperienciaRepository experienciaRepository;

    @Autowired
    private AreaTrabajoRepository areaTrabajoRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;


    @Override
    public Experiencia mapToEntity(ExperienciaDTO experienciaDTO) {
        Experiencia experiencia = new Experiencia();

        AreaTrabajo areaTrabajo= areaTrabajoRepository.findByNombre(experienciaDTO.getArea_trabajo())
                .orElseThrow(()-> new ResourceNotFoundException("nombre",experienciaDTO.getArea_trabajo()));

        Estudiante estudiante = estudianteRepository.findByCedula(experienciaDTO.getCedula())
                .orElseThrow(()-> new ResourceNotFoundException("cedula",experienciaDTO.getCedula()));

        experiencia.setId(experienciaDTO.getId());
        experiencia.setActividad(experienciaDTO.getActividad());
        experiencia.setCargo(experienciaDTO.getCargo());
        experiencia.setDuracion(experienciaDTO.getDuracion());
        experiencia.setInstitucion(experienciaDTO.getInstitucion());
        experiencia.setEstudiante(estudiante);
        experiencia.setAreaTrabajo(areaTrabajo);


        return experiencia;
    }

    @Override
    public ExperienciaDTO mapToDTO(Experiencia experiencia) {

        ExperienciaDTO experienciaDTO = new ExperienciaDTO();
        experienciaDTO.setId(experiencia.getId());
        experienciaDTO.setInstitucion(experiencia.getInstitucion());
        experienciaDTO.setCargo(experiencia.getCargo());
        experienciaDTO.setDuracion(experiencia.getDuracion());
        experienciaDTO.setActividad(experiencia.getActividad());
        experienciaDTO.setCedula(experiencia.getEstudiante().getCedula());
        experienciaDTO.setArea_trabajo(experiencia.getAreaTrabajo().getNombre());

        return experienciaDTO;
    }
    @Override
    public List findAll() {
        return experienciaRepository.findAll()
                .stream()
                .map(c -> mapToDTO(c))
                .collect(Collectors.toList());
    }

    @Override
    public Experiencia save(Object entity) {
        return experienciaRepository.save(mapToEntity((ExperienciaDTO) entity));
    }


    public ExperienciaDTO findByIdToDTO(long id) {
        return mapToDTO(experienciaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id", id)));
    }








}
