package ec.edu.ista.springgc1.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class EmpresaDTO implements Serializable {

    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String sectorEmpresarial;

    @NotEmpty
    private String ruc;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String tipoEmpresa;

    @NotEmpty
    private String razonSocial;

    @NotEmpty
    private String departamento;

    @NotEmpty
    private String ciudad;

    @NotEmpty
    private String direccion;

    @NotEmpty
    private String sitioWeb;
}
