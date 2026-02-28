package mx.unam.aragon.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcliente")
    private Long id;

    @NotBlank
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "num_cliente")
    private String numCliente;

    @NotBlank
    @Size(min = 10,max = 10,message = "Teléfono debe ser a 10 dígitos")
    @Column(name = "telefono")
    private String telefono;

    @NotBlank
    @Column(name = "email")
    private String email;

//    @OneToMany(mappedBy = "cliente")
//    private List<VentaEntity> ventas;

}
