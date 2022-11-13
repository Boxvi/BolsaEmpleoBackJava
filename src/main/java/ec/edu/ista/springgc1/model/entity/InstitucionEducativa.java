package ec.edu.ista.springgc1.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "institucion_educativa")
public class InstitucionEducativa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inst_id")
    private Long id;

    @NotEmpty
    @Column(nullable = false, length = 100)
    private String nombre;

}
