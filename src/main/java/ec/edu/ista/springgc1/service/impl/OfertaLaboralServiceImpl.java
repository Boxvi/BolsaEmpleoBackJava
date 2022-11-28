package ec.edu.ista.springgc1.service.impl;


import ec.edu.ista.springgc1.exception.ResourceNotFoundException;
import ec.edu.ista.springgc1.model.dto.OfertaLaboralDTO;
import ec.edu.ista.springgc1.model.entity.Ciudad;
import ec.edu.ista.springgc1.model.entity.Empresa;
import ec.edu.ista.springgc1.model.entity.OfertaLaboral;
import ec.edu.ista.springgc1.repository.CiudadRepository;
import ec.edu.ista.springgc1.repository.EmpresaRepository;
import ec.edu.ista.springgc1.repository.OfertaLaboralRepository;
import ec.edu.ista.springgc1.service.generic.impl.GenericServiceImpl;
import ec.edu.ista.springgc1.service.map.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfertaLaboralServiceImpl extends GenericServiceImpl<OfertaLaboral> implements Mapper<OfertaLaboral, OfertaLaboralDTO> {

    @Autowired
    private OfertaLaboralRepository ofertaLaboralRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private CiudadRepository ciudadRepository;


    @Override
    public OfertaLaboral mapToEntity(OfertaLaboralDTO ofertaLaboralDTO) {
        OfertaLaboral ofertaLaboral = new OfertaLaboral();

        Empresa empresa = empresaRepository.findByNombre(ofertaLaboralDTO.getEmpresa())
                .orElseThrow(() -> new ResourceNotFoundException("nombre", ofertaLaboralDTO.getEmpresa()));

        Ciudad ciudad = ciudadRepository.findByNombre(ofertaLaboralDTO.getCiudad())
                .orElseThrow(() -> new ResourceNotFoundException("nombre", ofertaLaboralDTO.getCiudad()));


        ofertaLaboral.setId(ofertaLaboralDTO.getId());
        ofertaLaboral.setCargo(ofertaLaboralDTO.getCargo());
        ofertaLaboral.setDescripcion(ofertaLaboralDTO.getDescripcion());
        ofertaLaboral.setArea_conocimiento(ofertaLaboralDTO.getArea_conocimiento());
        ofertaLaboral.setSalario(ofertaLaboralDTO.getSalario());
        ofertaLaboral.setJornada(ofertaLaboralDTO.getJornada());
        ofertaLaboral.setRequisitos_academicos(ofertaLaboralDTO.getRequisitos_academicos());
        ofertaLaboral.setExperiencia(ofertaLaboralDTO.getExperiencia());
        ofertaLaboral.setUbicacion(ofertaLaboralDTO.getUbicacion());
        ofertaLaboral.setFecha_inicio(ofertaLaboralDTO.getFecha_inicio());
        ofertaLaboral.setFecha_fin(ofertaLaboralDTO.getFecha_fin());
        ofertaLaboral.setEmpresa(empresa);
        ofertaLaboral.setCiudad(ciudad);


        return ofertaLaboral;
    }

    @Override
    public OfertaLaboralDTO mapToDTO(OfertaLaboral ofertaLaboral) {

        OfertaLaboralDTO ofertaLaboralDTO = new OfertaLaboralDTO();
        ofertaLaboralDTO.setId(ofertaLaboral.getId());
        ofertaLaboralDTO.setCargo(ofertaLaboral.getCargo());
        ofertaLaboralDTO.setDescripcion(ofertaLaboral.getDescripcion());
        ofertaLaboralDTO.setArea_conocimiento(ofertaLaboral.getArea_conocimiento());
        ofertaLaboralDTO.setSalario(ofertaLaboral.getSalario());
        ofertaLaboralDTO.setJornada(ofertaLaboral.getJornada());
        ofertaLaboralDTO.setRequisitos_academicos(ofertaLaboral.getRequisitos_academicos());
        ofertaLaboralDTO.setExperiencia(ofertaLaboral.getExperiencia());
        ofertaLaboralDTO.setUbicacion(ofertaLaboral.getUbicacion());
        ofertaLaboralDTO.setFecha_inicio(ofertaLaboral.getFecha_inicio());
        ofertaLaboralDTO.setFecha_fin(ofertaLaboral.getFecha_fin());
        ofertaLaboralDTO.setCiudad(ofertaLaboral.getCiudad().getNombre());
        ofertaLaboralDTO.setEmpresa(ofertaLaboral.getEmpresa().getNombre());


        return ofertaLaboralDTO;


    }

    @Override
    public List findAll() {
        return ofertaLaboralRepository.findAll()
                .stream()
                .map(c -> mapToDTO(c))
                .collect(Collectors.toList());
    }

    public List findByEmpresa(long empresa_id) {
        return ofertaLaboralRepository.findByEmpresaId(empresa_id)
                .stream()
                .map(em -> mapToDTO(em))
                .collect(Collectors.toList());
    }

    @Override
    public OfertaLaboral save(Object entity) {
        return ofertaLaboralRepository.save(mapToEntity((OfertaLaboralDTO) entity));
    }


    public OfertaLaboralDTO findByIdToDTO(long id) {
        return mapToDTO(ofertaLaboralRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id", id)));
    }

    public Optional<OfertaLaboral> findByCargo(String cargo) {

        return ofertaLaboralRepository.findByCargo(cargo);
    }

    public List findByCargoIgnoringCase(String cargo) {
        return ofertaLaboralRepository.findByCargoContaining(cargo)
                .stream()
                .map(ofertaLaboral -> mapToDTO(ofertaLaboral))
                .collect(Collectors.toList());
    }

    public Long countOfertas() {
        return ofertaLaboralRepository.count();
    }

}

