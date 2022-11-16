package ec.edu.ista.springgc1.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class PostulacionDTO implements Serializable {

    private Long id;

    private Date fecha;

    private String estado;

    private String cargo;

    private String cedula;


}