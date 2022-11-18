package ec.edu.ista.springgc1.model.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class ReferenciaProfesionalDTO implements Serializable {

    private Long id;

    @NotEmpty
    private String cedula;


    private String institucion;


    private String nombre;


    private String telefono;

    @NotEmpty
    @Email
    private String email;


}
