package mx.unam.aragon.service;

import mx.unam.aragon.model.entity.ArticuloEntity;
import mx.unam.aragon.model.entity.DetalleVentaEntity;
import mx.unam.aragon.model.entity.VentaEntity;

import java.util.List;

public interface DetalleVentaService {
    DetalleVentaEntity save(DetalleVentaEntity detalleVentaEntity);
    List<DetalleVentaEntity> findAll();
    void deleteById(long id);
    DetalleVentaEntity findById(long id);

    List<DetalleVentaEntity> findByVentaId(long id);

    DetalleVentaEntity findByVentaAndArticulo(VentaEntity venta, ArticuloEntity articulo);
    void deleteByVenta(VentaEntity venta);
}
