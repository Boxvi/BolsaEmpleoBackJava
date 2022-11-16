package ec.edu.ista.springgc1.service.impl;
import ec.edu.ista.springgc1.exception.ResourceNotFoundException;
import ec.edu.ista.springgc1.model.dto.EducacionDTO;
import ec.edu.ista.springgc1.model.entity.*;
import ec.edu.ista.springgc1.repository.*;
import ec.edu.ista.springgc1.service.map.Mapper;
import ec.edu.ista.springgc1.service.generic.impl.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EducacionServiceImpl extends GenericServiceImpl<Educacion> implements Mapper<Educacion, EducacionDTO>{

    @Autowired
    private EducacionRepository educacionRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private NivelRepository nivelRepository;

    @Autowired
    private InstEduRepository instEduRepository;

    @Autowired
    private AreaEstudioRepository areaEstudioRepository;


    @Override
    public Educacion mapToEntity(EducacionDTO educacionDTO) {

        Educacion educacion = new Educacion();
        Estudiante estudiante = estudianteRepository.findByCedula(educacionDTO.getCedula())
                .orElseThrow(()-> new ResourceNotFoundException("cedula",educacionDTO.getCedula()));

        Nivel nivel = nivelRepository.findByNombre(educacionDTO.getNivel())
                .orElseThrow(()-> new ResourceNotFoundException("nivel",educacionDTO.getNivel()));

        AreaEstudio areaEstudio = areaEstudioRepository.findByNombre(educacionDTO.getArea_estudio())
                .orElseThrow(()-> new ResourceNotFoundException("nivel",educacionDTO.getArea_estudio()));

        InstitucionEducativa institucionEducativa = instEduRepository.findByNombre(educacionDTO.getInstitucion_educativa())
                .orElseThrow(()-> new ResourceNotFoundException("institucion_educativa",educacionDTO.getInstitucion_educativa()));
        educacion.setId(educacionDTO.getId());
        educacion.setAnio(educacionDTO.getAnio());
        educacion.setTitulo(educacionDTO.getTitulo());
        educacion.setEstudiante(estudiante);
        educacion.setNivel(nivel);
        educacion.setAreaEstudio(areaEstudio);
        educacion.setInstitucionEducativa(institucionEducativa);


        return educacion;
    }

    @Override
    public EducacionDTO mapToDTO(Educacion educacion) {
        EducacionDTO educacionDTO = new EducacionDTO();

        educacionDTO.setId(educacion.getId());
        educacionDTO.setAnio(educacion.getAnio());
        educacionDTO.setTitulo(educacion.getTitulo());
        educacionDTO.setCedula(educacion.getEstudiante().getCedula());
        educacionDTO.setArea_estudio(educacion.getAreaEstudio().getNombre());
        educacionDTO.setNivel(educacion.getNivel().getNombre());
        educacionDTO.setInstitucion_educativa(educacion.getInstitucionEducativa().getNombre());

        return educacionDTO;
    }

    @Override
    public List findAll() {
        return educacionRepository.findAll()
                .stream()
                .map(c -> mapToDTO(c))
                .collect(Collectors.toList());
    }

    @Override
    public Educacion save(Object entity) {
        return educacionRepository.save(mapToEntity((EducacionDTO) entity));
    }

    public EducacionDTO findByIdToDTO(long id) {
        return mapToDTO(educacionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id", id)));
    }

    public Optional<Educacion> findByTitulo  (String titulo){
        return educacionRepository.findByTitulo(titulo);
    }


}
