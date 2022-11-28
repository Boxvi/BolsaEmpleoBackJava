package ec.edu.ista.springgc1.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "logro")
public class Logro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "logro_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;


    @Column(name = "tipo_logro", length = 100)
    private String tipoLogro;


    @Column(length = 150)
    private String descripcion;

}
