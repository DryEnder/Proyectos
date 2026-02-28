package mx.unam.aragon.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "venta")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VentaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idventa")
    private Long id;

    @Column(name = "num_comprobante")
    private String numComprobante;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    @Column(name = "total")
    private float total;

    @Column(name = "completado",nullable = false, columnDefinition = "TINYINT(1)")
    private boolean completado;

    @ManyToOne
    @JoinColumn(name = "idcliente", nullable = false)
    private ClienteEntity cliente;

//    @ManyToOne
//    @JoinColumn(name = "idusuario", nullable = false)
//    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "idalmacen", nullable = false)
    private AlmacenEntity almacen;


//    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<DetalleVentaEntity> detalles;

}