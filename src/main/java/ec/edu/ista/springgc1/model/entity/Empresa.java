package ec.edu.ista.springgc1.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "sec_emp_id")
    private SectorEmpresarial sectorEmpresarial;

    @Column(name = "RUC")
    private String ruc;

    private String nombre;

    @Column(name = "tipo_empresa")
    private String tipoEmpresa;

    @Column(name = "razon_social")
    private String razonSocial;

    private String departamento;

    @ManyToOne
    @JoinColumn(name = "ciu_id")
    private Ciudad ciudad;

    private String direccion;

    @Column(name = "sitio_web")
    private String sitioWeb;
}
