package ec.edu.ista.springgc1.service.impl;

import ec.edu.ista.springgc1.exception.ResourceNotFoundException;
import ec.edu.ista.springgc1.model.dto.ContactoEmpresaDTO;
import ec.edu.ista.springgc1.model.entity.ContactoEmpresa;
import ec.edu.ista.springgc1.model.entity.Empresa;
import ec.edu.ista.springgc1.repository.EmpresaRepository;
import ec.edu.ista.springgc1.repository.ContactoEmpresaRepository;
import ec.edu.ista.springgc1.service.generic.impl.GenericServiceImpl;
import ec.edu.ista.springgc1.service.map.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactoEmpresaServiceImpl extends GenericServiceImpl<ContactoEmpresa> implements Mapper<ContactoEmpresa, ContactoEmpresaDTO> {

    @Autowired
    private ContactoEmpresaRepository contactoEmpresaRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public ContactoEmpresa mapToEntity(ContactoEmpresaDTO contactoEmpresaDTO) {
        ContactoEmpresa contactoEmpresa = new ContactoEmpresa();
        Empresa empresa = empresaRepository.findById(contactoEmpresaDTO.getEmpresa_id())
                .orElseThrow(() -> new ResourceNotFoundException("id", contactoEmpresa.getId()));

        contactoEmpresa.setId(contactoEmpresaDTO.getId());
        contactoEmpresa.setEmpresa(empresa);
        contactoEmpresa.setNombre(contactoEmpresaDTO.getNombre());
        contactoEmpresa.setCargo(contactoEmpresaDTO.getCargo());
        contactoEmpresa.setTelefono(contactoEmpresaDTO.getTelefono());
        contactoEmpresa.setEmail(contactoEmpresaDTO.getEmail());
        return contactoEmpresa;
    }

    @Override
    public ContactoEmpresaDTO mapToDTO(ContactoEmpresa contactoEmpresa) {
        ContactoEmpresaDTO contactoEmpresaDTO = new ContactoEmpresaDTO();

        contactoEmpresaDTO.setId(contactoEmpresa.getId());
        contactoEmpresaDTO.setEmpresa_id(contactoEmpresa.getEmpresa().getId());
        contactoEmpresaDTO.setNombre(contactoEmpresa.getNombre());
        contactoEmpresaDTO.setCargo(contactoEmpresa.getCargo());
        contactoEmpresaDTO.setTelefono(contactoEmpresa.getTelefono());
        contactoEmpresaDTO.setEmail(contactoEmpresa.getEmail());

        return contactoEmpresaDTO;

    }

    @Override
    public List findAll() {
        return contactoEmpresaRepository.findAll()
                .stream()
                .map(ce -> mapToDTO(ce))
                .collect(Collectors.toList());
    }

    public List findByEmpresa(long empresa_id) {
        return contactoEmpresaRepository.findByEmpresaId(empresa_id)
                .stream()
                .map(cm -> mapToDTO(cm))
                .collect(Collectors.toList());
    }

    public ContactoEmpresaDTO findByIdToDTO(Long id) {
        return mapToDTO(contactoEmpresaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id", id)));
    }

    public Optional<ContactoEmpresa> findByNombre(String nombre) {
        return contactoEmpresaRepository.findByNombre(nombre);
    }

    @Override
    public ContactoEmpresa save(Object entity) {
        return contactoEmpresaRepository.save(mapToEntity((ContactoEmpresaDTO) entity));
    }
}
