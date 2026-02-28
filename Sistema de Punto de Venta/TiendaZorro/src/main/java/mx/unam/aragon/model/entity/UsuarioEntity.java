package mx.unam.aragon.model.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private Long id;

    @Column(name="nombre")
    private String nombre;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "idrol",nullable = false)
    private RolEntity rol;


//    @OneToMany(mappedBy = "usuario")
//    private List<VentaEntity> ventas;
//
//    @OneToMany(mappedBy = "usuario")
//    private List<IngresoEntity> ingresos;

}
