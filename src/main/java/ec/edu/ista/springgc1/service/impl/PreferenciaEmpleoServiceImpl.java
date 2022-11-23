package ec.edu.ista.springgc1.service.impl;


import ec.edu.ista.springgc1.exception.ResourceNotFoundException;
import ec.edu.ista.springgc1.model.dto.PreferenciaEmpleoDTO;
import ec.edu.ista.springgc1.model.entity.Estudiante;
import ec.edu.ista.springgc1.model.entity.PreferenciaEmpleo;
import ec.edu.ista.springgc1.repository.EstudianteRepository;
import ec.edu.ista.springgc1.repository.PreferenciaEmpleoRepository;
import ec.edu.ista.springgc1.service.generic.impl.GenericServiceImpl;
import ec.edu.ista.springgc1.service.map.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PreferenciaEmpleoServiceImpl extends GenericServiceImpl<PreferenciaEmpleo> implements Mapper<PreferenciaEmpleo, PreferenciaEmpleoDTO> {

    @Autowired
    private PreferenciaEmpleoRepository empleoRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;




    @Override
    public PreferenciaEmpleo mapToEntity(PreferenciaEmpleoDTO preferenciaEmpleoDTO) {
        PreferenciaEmpleo preferenciaEmpleo = new PreferenciaEmpleo();
        Estudiante estudiante = estudianteRepository.findByCedula(preferenciaEmpleoDTO.getCedula_estudiante())
                .orElseThrow(()-> new ResourceNotFoundException("cedula", preferenciaEmpleoDTO.getCedula_estudiante()));

        preferenciaEmpleo.setId(preferenciaEmpleoDTO.getId());
        preferenciaEmpleo.setSector_empresarial(preferenciaEmpleoDTO.getSector_empresarial());
        preferenciaEmpleo.setSalario(preferenciaEmpleoDTO.getSalario());
        preferenciaEmpleo.setTiempo(preferenciaEmpleoDTO.getTiempo());
        preferenciaEmpleo.setEstudiante(estudiante);


        return preferenciaEmpleo;
    }

    @Override
    public PreferenciaEmpleoDTO mapToDTO(PreferenciaEmpleo preferenciaEmpleo) {

        PreferenciaEmpleoDTO empleoDTO = new PreferenciaEmpleoDTO();
        empleoDTO.setId(preferenciaEmpleo.getId());
        empleoDTO.setSector_empresarial(preferenciaEmpleo.getSector_empresarial());
        empleoDTO.setSalario(preferenciaEmpleo.getSalario());
        empleoDTO.setTiempo(preferenciaEmpleo.getTiempo());
        empleoDTO.setCedula_estudiante(preferenciaEmpleo.getEstudiante().getCedula());

        return empleoDTO;
    }

    @Override
    public List findAll() {
        return empleoRepository.findAll()
                .stream()
                .map(c -> mapToDTO(c))
                .collect(Collectors.toList());
    }

    @Override
    public PreferenciaEmpleo save(Object entity) {
        return empleoRepository.save(mapToEntity((PreferenciaEmpleoDTO) entity));
    }

public List findByEstudiante (long estudiante_id){

    return empleoRepository.findByEstudiante(estudiante_id)
            .stream()
            .map(c -> mapToDTO(c))
            .collect(Collectors.toList());
}

    public PreferenciaEmpleoDTO findByIdToDTO(long id) {
        return mapToDTO(empleoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id", id)));
    }



}

