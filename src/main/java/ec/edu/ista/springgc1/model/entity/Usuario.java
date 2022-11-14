package ec.edu.ista.springgc1.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String telefono;

    private boolean estado;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;


    @PrePersist
    public void setCurrentDateTime(){
        fechaCreacion = LocalDate.now();
    }

}
