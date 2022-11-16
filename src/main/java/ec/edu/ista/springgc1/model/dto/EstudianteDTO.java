package ec.edu.ista.springgc1.model.dto;

import lombok.Data;

import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;

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

    private LocalDate fechaNacimiento;
    @NotEmpty
    private String ciudad;
    private String direccion;
    private String estadoCivil;

    private String rutaImagen;
    private String urlImagen;
}
