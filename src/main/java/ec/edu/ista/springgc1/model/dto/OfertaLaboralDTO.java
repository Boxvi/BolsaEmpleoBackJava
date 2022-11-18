package ec.edu.ista.springgc1.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class OfertaLaboralDTO implements Serializable {

    private Long id;


    private  String cargo;

    private  String descripcion;

    private String area_conocimiento;

    private String salario;

    private String jornada;

    private String requisitos_academicos;

    private String experiencia;


    private  String ubicacion;

    private Date fecha_inicio;

    private Date fecha_fin;

    private String empresa;

    private String ciudad;


}
