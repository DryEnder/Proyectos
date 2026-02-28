package mx.unam.aragon.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "ingreso")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngresoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idingreso")
    private Long id;

    @Column(name = "num_comprobante")
    private String numComprobante;

    @Column(name="fecha")
    private LocalDateTime fecha;

    @Column(name="total")
    private float total;

    @ManyToOne
    @JoinColumn(name = "idproveedor", nullable = false)
    private ProveedorEntity proveedor;

    @ManyToOne
    @JoinColumn(name = "idusuario", nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "idalmacen", nullable = false)
    private AlmacenEntity almacen;


//    @OneToMany(mappedBy = "ingreso", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<DetalleIngresoEntity> detalles;


}
