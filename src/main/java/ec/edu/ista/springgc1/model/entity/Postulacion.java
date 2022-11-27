package ec.edu.ista.springgc1.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "postulaciones")
public class Postulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postulacion_id")
    private Long id;

    private LocalDate fecha;

    private String estado;

    //relacion con empresa
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "oferta_id")
    private OfertaLaboral ofertaLaboral;

    //relacion con estudiante
    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    @PrePersist
    public void setCurrentDateTime() {
        fecha = LocalDate.now();
    }

}
