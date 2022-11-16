package ec.edu.ista.springgc1.service.impl;

import ec.edu.ista.springgc1.exception.ResourceNotFoundException;
import ec.edu.ista.springgc1.model.dto.CiudadDTO;
import ec.edu.ista.springgc1.model.dto.EstudianteDTO;
import ec.edu.ista.springgc1.model.dto.LogroDTO;
import ec.edu.ista.springgc1.model.entity.Ciudad;
import ec.edu.ista.springgc1.model.entity.Estudiante;
import ec.edu.ista.springgc1.model.entity.Logro;
import ec.edu.ista.springgc1.repository.EstudianteRepository;
import ec.edu.ista.springgc1.repository.LogroRepository;
import ec.edu.ista.springgc1.service.generic.impl.GenericServiceImpl;
import ec.edu.ista.springgc1.service.map.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LogroServiceImpl extends GenericServiceImpl<Logro> implements Mapper<Logro, LogroDTO> {

    @Autowired
    private LogroRepository logroRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public Logro mapToEntity(LogroDTO logroDTO) {

        Logro logro = new Logro();
        Estudiante estudiante = estudianteRepository.findByCedula(logroDTO.getCedula())
                .orElseThrow(()-> new ResourceNotFoundException("cedula", logroDTO.getCedula()));

        logro.setId(logroDTO.getId());
        logro.setTipoLogro(logroDTO.getTipoLogro());
        logro.setDescripcion(logroDTO.getDescripcion());
        logro.setEstudiante(estudiante);
        return logro;
    }

    @Override
    public LogroDTO mapToDTO(Logro logro) {

        LogroDTO logroDTO = new LogroDTO();
        logroDTO.setId(logro.getId());
        logroDTO.setTipoLogro(logro.getTipoLogro());
        logroDTO.setDescripcion(logro.getDescripcion());
        logroDTO.setCedula(logro.getEstudiante().getCedula());

        return logroDTO;
    }


    @Override
    public List findAll() {
        return logroRepository.findAll()
                .stream()
                .map(c -> mapToDTO(c))
                .collect(Collectors.toList());
    }

    public LogroDTO findByIdToDTO(Long id){
        return mapToDTO(logroRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("id", id)));
    }


    @Override
    public Logro save(Object entity) {
        return logroRepository.save(mapToEntity((LogroDTO) entity));
    }



    public Optional<Logro> findByTipoLogro(String tipoLogro){return logroRepository.findByTipoLogro(tipoLogro) ;}

}
