package ec.edu.ista.springgc1.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
@Data
public class PerfilOcupacionalDTO implements Serializable {

    private Long id;

    @NotEmpty
    private  String  cedula;
    @NotEmpty
    private  String habilidades;
    @NotEmpty
    private String destrezas;
    @NotEmpty
    private  String actitudes;


}

