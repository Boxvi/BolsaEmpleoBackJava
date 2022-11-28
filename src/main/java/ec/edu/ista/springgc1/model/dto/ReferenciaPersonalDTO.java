package ec.edu.ista.springgc1.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class ReferenciaPersonalDTO  implements Serializable {

    private Long id;

    @NotEmpty
    private String cedula;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String telefono;



}
