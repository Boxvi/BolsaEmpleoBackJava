package ec.edu.ista.springgc1.model.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Data
public class CapacitacionDTO implements Serializable {

    private Long id;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String cedula;


    private String institucion;


    private String tipoCapacitacion;


    private String tipoCertificado;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fechaInicio;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fechaFin;


    private Integer numHoras;
}
