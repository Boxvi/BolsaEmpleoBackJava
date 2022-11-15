package ec.edu.ista.springgc1.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ciudad")
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ciu_id")
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "prov_id")
    private Provincia provincia;
}
