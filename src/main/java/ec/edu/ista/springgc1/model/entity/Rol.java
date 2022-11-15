package ec.edu.ista.springgc1.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Entity
@Table(name = "rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< Updated upstream

=======
    @Column(name = "rol_id")
>>>>>>> Stashed changes
    private Long rol_id;

    @NotEmpty
    @Column(nullable = false, length = 20)
    private String nombre;

    @NotEmpty
    @Column(length = 255)
    private String descripcion;

    /*@OneToMany(mappedBy = "rol", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Usuario> usuarios;*/
}
