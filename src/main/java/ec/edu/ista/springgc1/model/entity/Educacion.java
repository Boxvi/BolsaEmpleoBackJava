package ec.edu.ista.springgc1.model.entity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "educacion")
public class Educacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "educacion_id")
    private Long id;


    private int anio;

    private String titulo;

    //relacion con estudiante

    @ManyToOne
    @JoinColumn(name="estudiante_id")
    private Estudiante estudiante;

    //relacion con niveles
    @ManyToOne
    @JoinColumn(name="nivel_id")
    private Nivel nivel;

    //relacion con instituciones

    @ManyToOne
    @JoinColumn(name = "inst_id")
    private InstitucionEducativa institucionEducativa;

    //relacion con areas estudio

    @ManyToOne
    @JoinColumn(name = "areaEstudio_id")
    private AreaEstudio areaEstudio;




}
