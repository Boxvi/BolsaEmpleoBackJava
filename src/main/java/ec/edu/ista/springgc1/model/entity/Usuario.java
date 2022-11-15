package ec.edu.ista.springgc1.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< Updated upstream
=======
    @Column(name = "usuario_id")
>>>>>>> Stashed changes
    private Long usuario_id;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    private String username;

    private String password;

    private String email;

    private String telefono;

    private boolean estado;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;


    @PrePersist
    public void setCurrentDateTime() {
        fechaCreacion = LocalDate.now();
    }

}
