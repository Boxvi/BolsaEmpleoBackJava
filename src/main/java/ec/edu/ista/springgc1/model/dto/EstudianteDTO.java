package ec.edu.ista.springgc1.model.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Data
public class EstudianteDTO implements Serializable {

    private Long id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String cedula;
    @NotEmpty
    private String nombres;
    @NotEmpty
    private String apellidos;
    private char genero;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fechaNacimiento;
    @NotEmpty
    private String ciudad;
    private String direccion;
    private String estadoCivil;
    private String fotografia;
}
