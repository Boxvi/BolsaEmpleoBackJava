package ec.edu.ista.springgc1.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "experiencias")
public class Experiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "experiencia_id")
    private Long id;

    private String institucion;
    private String cargo;
    private String duracion;
    private String actividad;

    //relacion con estudiante
    @ManyToOne
    @JoinColumn(name="estudiante_id")
    private Estudiante estudiante;

    //relacion con area de trabajo
    @ManyToOne
    @JoinColumn(name = "areaTrabajo_id")
    private AreaTrabajo areaTrabajo;
}
