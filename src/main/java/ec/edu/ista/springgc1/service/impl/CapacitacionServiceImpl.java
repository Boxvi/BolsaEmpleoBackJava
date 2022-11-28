package ec.edu.ista.springgc1.service.impl;

import ec.edu.ista.springgc1.exception.ResourceNotFoundException;
import ec.edu.ista.springgc1.model.dto.CapacitacionDTO;
import ec.edu.ista.springgc1.model.dto.EstudianteDTO;
import ec.edu.ista.springgc1.model.dto.ReferenciaPersonalDTO;
import ec.edu.ista.springgc1.model.entity.AreaEstudio;
import ec.edu.ista.springgc1.model.entity.Capacitacion;
import ec.edu.ista.springgc1.model.entity.Estudiante;
import ec.edu.ista.springgc1.model.entity.ReferenciaPersonal;
import ec.edu.ista.springgc1.repository.AreaEstudioRepository;
import ec.edu.ista.springgc1.repository.CapacitacionRepository;
import ec.edu.ista.springgc1.repository.EstudianteRepository;
import ec.edu.ista.springgc1.service.generic.impl.GenericServiceImpl;
import ec.edu.ista.springgc1.service.map.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CapacitacionServiceImpl extends GenericServiceImpl<Capacitacion> implements Mapper<Capacitacion, CapacitacionDTO> {

    @Autowired
    private CapacitacionRepository capacitacionRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private AreaEstudioRepository areaEstudioRepository;


    @Override
    public Capacitacion mapToEntity(CapacitacionDTO capacitacionDTO) {

        Capacitacion capacitacion = new Capacitacion();
        Estudiante estudiante = estudianteRepository.findByCedula(capacitacionDTO.getCedula())
                .orElseThrow(()-> new ResourceNotFoundException("cedula", capacitacionDTO.getCedula()));

        AreaEstudio areaEstudio = areaEstudioRepository.findByNombre(capacitacionDTO.getArea_estudio())
                .orElseThrow(()-> new ResourceNotFoundException("nombre", capacitacionDTO.getArea_estudio()));

        capacitacion.setId(capacitacionDTO.getId());
        capacitacion.setNombre_capacitacion(capacitacionDTO.getNombre_capacitacion());
        capacitacion.setInstitucion(capacitacionDTO.getInstitucion());
        capacitacion.setTipoCapacitacion(capacitacionDTO.getTipoCapacitacion());
        capacitacion.setTipoCertificado(capacitacionDTO.getTipoCertificado());
        capacitacion.setFechaInicio(capacitacionDTO.getFechaInicio());
        capacitacion.setFechaFin(capacitacionDTO.getFechaFin());
        capacitacion.setNumHoras(capacitacionDTO.getNumHoras());
        capacitacion.setEstudiante(estudiante);
        capacitacion.setAreaEstudio(areaEstudio);
        return capacitacion;
    }

    @Override
    public CapacitacionDTO mapToDTO(Capacitacion capacitacion) {

        CapacitacionDTO capacitacionDTO = new CapacitacionDTO();
        capacitacionDTO.setId(capacitacion.getId());
        capacitacionDTO.setInstitucion(capacitacion.getInstitucion());
        capacitacionDTO.setTipoCapacitacion(capacitacion.getTipoCapacitacion());
        capacitacionDTO.setTipoCertificado(capacitacion.getTipoCertificado());
        capacitacionDTO.setFechaInicio(capacitacion.getFechaInicio());
        capacitacionDTO.setFechaFin(capacitacion.getFechaFin());
        capacitacionDTO.setNumHoras(capacitacion.getNumHoras());
        capacitacionDTO.setCedula(capacitacion.getEstudiante().getCedula());
        capacitacionDTO.setNombre_capacitacion(capacitacion.getNombre_capacitacion());
        capacitacionDTO.setArea_estudio(capacitacion.getAreaEstudio().getNombre());

        return capacitacionDTO;
    }


    @Override
    public List findAll() {
        return capacitacionRepository.findAll()
                .stream()
                .map(c -> mapToDTO(c))
                .collect(Collectors.toList());
    }

    public CapacitacionDTO findByIdToDTO(Long id){
        return mapToDTO(capacitacionRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("id", id)));
    }

    public List findByEstudiante(long estudiante_id){
        return capacitacionRepository.findByEstudiante(estudiante_id)
                .stream()
                .map(e -> mapToDTO(e))
                .collect(Collectors.toList());
    }

    @Override
    public Capacitacion save(Object entity) {
        return capacitacionRepository.save(mapToEntity((CapacitacionDTO)  entity));
    }


    public Optional<Capacitacion> findByInstitucion(String institucion) {return capacitacionRepository.findByInstitucion(institucion);}

    public  List<Capacitacion> findByCedulaCapacitacions (String cedula){
        return capacitacionRepository.findByCedulaCapacitacions(cedula);
    }



}
