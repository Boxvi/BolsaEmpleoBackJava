package ec.edu.ista.springgc1.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "estudiante")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estudiante_id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(length = 10)
    private String cedula;

    @Column(length = 45)
    private String nombres;

    @Column(length = 45)
    private String apellidos;

    private char genero;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "ciu_id")
    private Ciudad ciudad;

    private String direccion;
    @Column(name = "estado_civil")
    private String estadoCivil;

    private String fotografia;
}
