package mx.unam.aragon.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "almacen")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlmacenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idalmacen")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

//    @OneToMany(mappedBy = "almacen")
//    private List<InventarioEntity> inventarios;

//    @OneToMany(mappedBy = "almacen")
//    private List<VentaEntity> ventas;
//
//    @OneToMany(mappedBy = "almacen")
//    private List<IngresoEntity> ingresos;

}
