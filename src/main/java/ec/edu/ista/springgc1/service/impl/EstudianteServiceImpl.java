package ec.edu.ista.springgc1.service.impl;

import ec.edu.ista.springgc1.exception.ResourceNotFoundException;
import ec.edu.ista.springgc1.model.dto.EstudianteDTO;
import ec.edu.ista.springgc1.model.entity.Ciudad;
import ec.edu.ista.springgc1.model.entity.Estudiante;
import ec.edu.ista.springgc1.model.entity.Usuario;
import ec.edu.ista.springgc1.repository.CiudadRepository;
import ec.edu.ista.springgc1.repository.EstudianteRepository;
import ec.edu.ista.springgc1.repository.generic.UsuarioRepository;
import ec.edu.ista.springgc1.service.generic.impl.GenericServiceImpl;
import ec.edu.ista.springgc1.service.map.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class EstudianteServiceImpl extends GenericServiceImpl<Estudiante> implements Mapper<Estudiante, EstudianteDTO> {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Override
    public Estudiante mapToEntity(EstudianteDTO estudianteDTO) {
        Estudiante estudiante = new Estudiante();

        Usuario usuario = usuarioRepository.findByUsername(estudianteDTO.getUsername())
                .orElseThrow(()-> new ResourceNotFoundException("username",estudianteDTO.getUsername()));

        Ciudad ciudad = ciudadRepository.findByNombre(estudianteDTO.getCiudad())
                .orElseThrow(() -> new ResourceNotFoundException("ciudad",estudianteDTO.getCiudad()));

        estudiante.setId(estudianteDTO.getId());
        estudiante.setUsuario(usuario);
        estudiante.setCedula(estudianteDTO.getCedula());
        estudiante.setNombres(estudianteDTO.getNombres());
        estudiante.setApellidos(estudianteDTO.getApellidos());
        estudiante.setGenero(estudianteDTO.getGenero());
        estudiante.setFechaNacimiento(estudianteDTO.getFechaNacimiento());
        estudiante.setCiudad(ciudad);
        estudiante.setDireccion(estudianteDTO.getDireccion());
        estudiante.setEstadoCivil(estudianteDTO.getEstadoCivil());
        estudiante.setFotografia(estudianteDTO.getFotografia());

        return estudiante;
    }

    @Override
    public EstudianteDTO mapToDTO(Estudiante estudiante) {
        EstudianteDTO estudianteDTO = new EstudianteDTO();

        estudianteDTO.setId(estudiante.getId());
        estudianteDTO.setUsername(estudiante.getUsuario().getUsername());
        estudianteDTO.setCedula(estudiante.getCedula());
        estudianteDTO.setNombres(estudiante.getNombres());
        estudianteDTO.setApellidos(estudiante.getApellidos());
        estudianteDTO.setGenero(estudiante.getGenero());
        estudianteDTO.setFechaNacimiento(estudiante.getFechaNacimiento());
        estudianteDTO.setCiudad(estudiante.getCiudad().getNombre());
        estudianteDTO.setDireccion(estudiante.getDireccion());
        estudianteDTO.setEstadoCivil(estudiante.getEstadoCivil());
        estudianteDTO.setFotografia(estudiante.getFotografia());

        return estudianteDTO;
    }

    @Override
    public List findAll() {
        return estudianteRepository.findAll()
                .stream()
                .map(e -> mapToDTO(e))
                .collect(Collectors.toList());
    }

    public EstudianteDTO findByIdToDTO(Long id){
        return mapToDTO(estudianteRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("id", id)));
    }

    public Boolean existsByCedula(String cedula){
        return estudianteRepository.existsByCedula(cedula);
    }

    @Override
    public Estudiante save(Object entity) {
        return estudianteRepository.save(mapToEntity((EstudianteDTO) entity));
    }
}
