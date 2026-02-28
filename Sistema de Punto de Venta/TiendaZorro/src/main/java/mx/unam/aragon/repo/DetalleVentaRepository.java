package mx.unam.aragon.repo;

import mx.unam.aragon.model.entity.ArticuloEntity;
import mx.unam.aragon.model.entity.DetalleVentaEntity;
import mx.unam.aragon.model.entity.UsuarioEntity;
import mx.unam.aragon.model.entity.VentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DetalleVentaRepository extends JpaRepository<DetalleVentaEntity, Long> {
    List<DetalleVentaEntity> findAllByVenta_Id(Long ventaId);

    Optional<DetalleVentaEntity> findByVenta_IdAndArticulo_Id(long idv, long idar);

    Optional<DetalleVentaEntity> findByVentaAndArticulo(VentaEntity venta, ArticuloEntity articulo);
}
