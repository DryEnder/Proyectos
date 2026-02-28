package mx.unam.aragon.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "categoria")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcategoria")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

//    @OneToMany(mappedBy = "categoria")
//    private List<ArticuloEntity> articulos;

}
