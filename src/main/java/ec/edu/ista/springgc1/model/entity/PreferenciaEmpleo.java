package ec.edu.ista.springgc1.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "preferenciaEmpleos")
public class PreferenciaEmpleo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "preferencia_id")
    private Long id;


    private String sector_empresarial;


    private String tiempo;



    private Double salario;



    //relacion con estudiante

    @OneToOne
    @JoinColumn(name = "estudiante_id",nullable = false)
    private Estudiante estudiante;
}
