package ec.edu.ista.springgc1.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "nivel")
public class Nivel {
    //tabla que almacena el nivel educativo de un estudiante
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "nivel_id", nullable = false)
    private Long id;

    @NotEmpty
    @Column(length = 255)
    private String nombre;



}
