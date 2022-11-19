package ec.edu.ista.springgc1.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
@Data
public class PostulacionDTO implements Serializable {

    private Long id;

    private LocalDateTime fecha;

    @NotEmpty
    private String estado;

    private Long ofertalaboral_id;

    @NotEmpty
    private String cedula;



}