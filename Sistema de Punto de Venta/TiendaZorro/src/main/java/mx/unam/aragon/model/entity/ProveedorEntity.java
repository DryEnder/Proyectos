package mx.unam.aragon.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "proveedor")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProveedorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproveedor")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "rfc")
    private String rfc;

    @Column(name = "email")
    private String email;


//    @OneToMany(mappedBy = "proveedor")
//    private List<IngresoEntity> ingresos;


}
