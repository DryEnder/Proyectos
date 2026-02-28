package mx.unam.aragon.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdInventario implements Serializable {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idarticulo",nullable = false)
    private ArticuloEntity articulo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idalmacen",nullable = false)
    private AlmacenEntity almacen;

}
