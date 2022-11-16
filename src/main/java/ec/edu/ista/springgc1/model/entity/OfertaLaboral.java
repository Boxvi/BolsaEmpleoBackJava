package ec.edu.ista.springgc1.model.entity;

import lombok.Data;
import java.util.Date;
import javax.persistence.*;

@Data
@Entity
@Table(name = "ofertaslaborales")
public class OfertaLaboral {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "oferta_id", nullable = false)
    private Long id;

    private String cargo;

    private  String descripcion;

    private String area_conocimiento;

    private  String salario;

    private  String jornada;

    private  String requisitos_academicos;

    private  String experiencia;

    private  String ubicacion;

    private Date fecha_inicio;

    private  Date fecha_fin;

    //relacion con empresa

    @ManyToOne
    @JoinColumn(name="emp_id")
    private Empresa empresa;

    //relacion con ciudad
    @ManyToOne
    @JoinColumn(name = "ciu_id")
    private Ciudad ciudad;



}

