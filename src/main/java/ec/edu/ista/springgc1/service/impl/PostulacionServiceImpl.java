package ec.edu.ista.springgc1.service.impl;


import ec.edu.ista.springgc1.exception.ResourceNotFoundException;
import ec.edu.ista.springgc1.model.dto.PostulacionDTO;
import ec.edu.ista.springgc1.model.entity.Estudiante;
import ec.edu.ista.springgc1.model.entity.OfertaLaboral;
import ec.edu.ista.springgc1.model.entity.Postulacion;
import ec.edu.ista.springgc1.repository.EstudianteRepository;
import ec.edu.ista.springgc1.repository.OfertaLaboralRepository;
import ec.edu.ista.springgc1.repository.PostulacionRepository;
import ec.edu.ista.springgc1.service.generic.impl.GenericServiceImpl;
import ec.edu.ista.springgc1.service.map.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostulacionServiceImpl extends GenericServiceImpl<Postulacion> implements Mapper<Postulacion, PostulacionDTO> {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private OfertaLaboralRepository ofertaLaboralRepository;

    @Autowired
    private PostulacionRepository postulacionRepository;


    @Override
    public Postulacion mapToEntity(PostulacionDTO postulacionDTO) {
        Postulacion postulacion = new Postulacion();

        Estudiante estudiante = estudianteRepository.findByCedula(postulacionDTO.getCedula())
                .orElseThrow(()-> new ResourceNotFoundException("cedula",postulacionDTO.getCedula()));
      /*  OfertaLaboral ofertaLaboral = ofertaLaboralRepository.findByCargo(postulacionDTO.getCargo())
                .orElseThrow(()-> new ResourceNotFoundException("cargo",postulacionDTO.getCargo()));*/
        OfertaLaboral ofertaLaboral = ofertaLaboralRepository.findById(postulacionDTO.getOfertalaboral_id())
                .orElseThrow(()-> new ResourceNotFoundException("id",postulacionDTO.getOfertalaboral_id()));


        postulacion.setId(postulacionDTO.getId());
        postulacion.setEstado(postulacionDTO.getEstado());
        postulacion.setFecha(postulacionDTO.getFecha());
        postulacion.setOfertaLaboral(ofertaLaboral) ;
        postulacion.setEstudiante(estudiante);


        return postulacion;
    }

    @Override
    public PostulacionDTO mapToDTO(Postulacion postulacion) {
        PostulacionDTO postulacionDTO = new PostulacionDTO();

        postulacionDTO.setId(postulacion.getId());
        postulacionDTO.setEstado(postulacion.getEstado());
        postulacionDTO.setFecha(postulacion.getFecha());
        postulacionDTO.setOfertalaboral_id(postulacion.getOfertaLaboral().getId());
        postulacionDTO.setCedula(postulacion.getEstudiante().getCedula());

        return postulacionDTO;
    }
    @Override
    public List findAll() {
        return postulacionRepository.findAll()
                .stream()
                .map(c -> mapToDTO(c))
                .collect(Collectors.toList());
    }

    @Override
    public Postulacion save(Object entity) {
        return postulacionRepository.save(mapToEntity((PostulacionDTO) entity));
    }


    public PostulacionDTO findByIdToDTO(long id) {
        return mapToDTO(postulacionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id", id)));
    }




}

