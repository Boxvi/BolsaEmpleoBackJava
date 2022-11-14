package ec.edu.ista.springgc1.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "sectorEmpresarial")
public class SectorEmpresarial {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "areaEstudio_id", nullable = false)
    private Long id;

    @NotEmpty
    @Column(length = 255)
    private String nombre_sector;



}
