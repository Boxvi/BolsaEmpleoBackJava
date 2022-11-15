package ec.edu.ista.springgc1.model.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class UsuarioDTO implements Serializable {

    private Long id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    @Email
    private String email;
    private String telefono;
    private boolean estado;
    private LocalDate fechaCreacion;
    @NotEmpty
    private String rol;
}
