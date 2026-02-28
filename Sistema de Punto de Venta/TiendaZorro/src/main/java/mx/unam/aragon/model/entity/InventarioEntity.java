package mx.unam.aragon.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity(name = "inventario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventarioEntity {
    /*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idinventario")
    private Long id;

     */
    @EmbeddedId
    private IdInventario idInventario;

    @Column(name="stock")
    private Integer stock;

    @Transient
    private Integer nuevoStock;

}
