package ec.edu.ista.springgc1.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class EducacionDTO implements Serializable {

    private Long id;


    private int anio;

    @NotEmpty
    private String titulo;

    @NotEmpty
    private String cedula;

    @NotEmpty
    private  String institucion_educativa;

    @NotEmpty
    private  String nivel;

    private String area_estudio;



}
