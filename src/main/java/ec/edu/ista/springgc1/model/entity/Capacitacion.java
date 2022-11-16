package ec.edu.ista.springgc1.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@Entity
@Table(name = "capacitacion")
public class Capacitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cap_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "areaEstudio_id")
    private AreaEstudio areaEstudio;


    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;



    private String institucion;


    @Column(name = "tipo_capacitacion")
    private String tipoCapacitacion;


    @Column(name = "tipo_certificado")
    private String tipoCertificado;


    @Column(name = "fecha_inicio")
    private Date fechaInicio;


    @Column(name = "fecha_fin")
    private Date fechaFin;


    @Column(name = "num_horas")
    private Integer numHoras;


}
