package ec.edu.ista.springgc1.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class PreferenciaEmpleoDTO implements Serializable {

    private Long id;

    @NotEmpty
    private String sector_empresarial;
    @NotEmpty
    private  String tiempo;


    private Double salario;


    @NotEmpty
    private  String cedula_estudiante;


}

