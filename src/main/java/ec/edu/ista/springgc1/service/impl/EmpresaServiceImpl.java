package ec.edu.ista.springgc1.service.impl;

import ec.edu.ista.springgc1.exception.ResourceNotFoundException;
import ec.edu.ista.springgc1.model.dto.EmpresaDTO;
import ec.edu.ista.springgc1.model.entity.Ciudad;
import ec.edu.ista.springgc1.model.entity.Empresa;
import ec.edu.ista.springgc1.model.entity.SectorEmpresarial;
import ec.edu.ista.springgc1.model.entity.Usuario;
import ec.edu.ista.springgc1.repository.CiudadRepository;
import ec.edu.ista.springgc1.repository.EmpresaRepository;
import ec.edu.ista.springgc1.repository.SectorEmpresarialRepository;
import ec.edu.ista.springgc1.repository.generic.UsuarioRepository;
import ec.edu.ista.springgc1.service.generic.impl.GenericServiceImpl;
import ec.edu.ista.springgc1.service.map.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaServiceImpl extends GenericServiceImpl<Empresa> implements Mapper<Empresa, EmpresaDTO> {

    @Autowired
    private EmpresaRepository empresaRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SectorEmpresarialRepository sectorEmpresarialRepository;
    
    @Autowired
    private CiudadRepository ciudadRepository;


    @Override
    public Empresa mapToEntity(EmpresaDTO empresaDTO) {

        Empresa empresa = new Empresa();
        Usuario usuario = usuarioRepository.findByUsername(empresaDTO.getUsername())
                .orElseThrow(()-> new ResourceNotFoundException("username",empresaDTO.getUsername()));
        SectorEmpresarial sectorEmpresarial = sectorEmpresarialRepository.findByNombre(empresaDTO.getSectorEmpresarial())
                .orElseThrow(()-> new ResourceNotFoundException("sector empresarial", empresaDTO.getSectorEmpresarial()));
        Ciudad ciudad = ciudadRepository.findByNombre(empresaDTO.getCiudad())
                .orElseThrow(() -> new ResourceNotFoundException("ciudad",empresaDTO.getCiudad()));

        empresa.setId(empresaDTO.getId());
        empresa.setUsuario(usuario);
        empresa.setSectorEmpresarial(sectorEmpresarial);
        empresa.setRuc(empresaDTO.getRuc());
        empresa.setNombre(empresaDTO.getNombre());
        empresa.setTipoEmpresa(empresaDTO.getTipoEmpresa());
        empresa.setRazonSocial(empresaDTO.getRazonSocial());
        empresa.setDepartamento(empresaDTO.getDepartamento());
        empresa.setCiudad(ciudad);
        empresa.setDireccion(empresaDTO.getDireccion());
        empresa.setSitioWeb(empresaDTO.getSitioWeb());

        return empresa;
    }

    @Override
    public EmpresaDTO mapToDTO(Empresa empresa) {

        EmpresaDTO empresaDTO = new EmpresaDTO();

        empresaDTO.setId(empresa.getId());
        empresaDTO.setUsername(empresa.getUsuario().getUsername());
        empresaDTO.setSectorEmpresarial(empresa.getSectorEmpresarial().getNombre());
        empresaDTO.setRuc(empresa.getRuc());
        empresaDTO.setNombre(empresa.getNombre());
        empresaDTO.setTipoEmpresa(empresa.getTipoEmpresa());
        empresaDTO.setRazonSocial(empresa.getRazonSocial());
        empresaDTO.setDepartamento(empresa.getDepartamento());
        empresaDTO.setCiudad(empresa.getCiudad().getNombre());
        empresaDTO.setDireccion(empresa.getDireccion());
        empresaDTO.setSitioWeb(empresa.getSitioWeb());

        return empresaDTO;
    }

    @Override
    public List findAll() {
        return empresaRepository.findAll()
                .stream()
                .map(e -> mapToDTO(e))
                .collect(Collectors.toList());
    }

    public EmpresaDTO findByIdToDTO(Long id){
        return mapToDTO(empresaRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("id", id)));
    }

    public Boolean existsByNombre(String nombre){
        return empresaRepository.existsByNombre(nombre);
    }
    public Boolean existsByRuc(String ruc){
        return empresaRepository.existsByRuc(ruc);
    }

    @Override
    public Empresa save(Object entity) {
        return empresaRepository.save(mapToEntity((EmpresaDTO) entity));
    }
}
