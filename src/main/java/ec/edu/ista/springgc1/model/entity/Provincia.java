package ec.edu.ista.springgc1.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "provincia")
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long prov_id;

    @NotEmpty
    @Column(nullable = false, length = 50)
    private String nombre;


    @NotEmpty
    @Column(nullable = false, length = 50)
    private String pais;


}
