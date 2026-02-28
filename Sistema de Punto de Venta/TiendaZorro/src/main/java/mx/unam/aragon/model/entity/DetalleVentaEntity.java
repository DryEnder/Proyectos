package mx.unam.aragon.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "detalle_venta")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleVentaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddetalle_venta")
    private Long id;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "precio")
    private float precio;

    @ManyToOne
    @JoinColumn(name = "idventa", nullable = false)
    private VentaEntity venta;

    @ManyToOne
    @JoinColumn(name = "idarticulo", nullable = false)
    private ArticuloEntity articulo;

}
