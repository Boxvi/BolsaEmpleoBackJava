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
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty
    @Column(nullable = false, length = 50)
  private String provincia;


    @NotEmpty
    @Column(nullable = false, length = 50)
    private String pais;


}
