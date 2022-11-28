package ec.edu.ista.springgc1.model.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ContactoEmpresaDTO implements Serializable {

    private Long id;

    @NotNull
    private Long empresa_id;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String cargo;

    @NotEmpty
    private String telefono;

    @NotEmpty
    @Email
    private String email;
}
