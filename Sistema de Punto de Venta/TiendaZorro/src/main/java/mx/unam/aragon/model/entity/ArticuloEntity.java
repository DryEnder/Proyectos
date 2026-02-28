package mx.unam.aragon.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "articulo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticuloEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idarticulo")
    private Long id;

    @Column(name="codigo")
    private String codigo;

    @Column(name="nombre")
    private String nombre;

    @Column(name="url_foto")
    private String urlFoto;

    //big decimal para los precios??
    @Column(name = "precio_venta")
    private float precioVenta;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "idcategoria", nullable = false)
    private CategoriaEntity categoria;

    @ManyToOne
    @JoinColumn(name = "idproveedor", nullable = false)
    private ProveedorEntity proveedor;

//    @OneToMany(mappedBy = "IdInventario.articulo")
//    private List<InventarioEntity> inventarios;

//    @OneToMany(mappedBy = "idInventario", fetch = FetchType.EAGER)
//    private List<InventarioEntity> inventarios;

//    @OneToMany(mappedBy = "articulo", fetch = FetchType.EAGER)
//    private List<DetalleVentaEntity> detallesVenta;
//
//    @OneToMany(mappedBy = "articulo", fetch = FetchType.EAGER)
//    private List<DetalleIngresoEntity> detallesIngreso;

}
