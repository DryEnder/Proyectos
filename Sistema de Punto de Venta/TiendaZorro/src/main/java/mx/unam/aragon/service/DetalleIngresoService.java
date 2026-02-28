package mx.unam.aragon.service;

import mx.unam.aragon.model.entity.DetalleIngresoEntity;

import java.util.List;

public interface DetalleIngresoService {
    DetalleIngresoEntity save(DetalleIngresoEntity detalleIngresoEntity);
    List<DetalleIngresoEntity> findAll();
    void deleteById(long id);
    DetalleIngresoEntity findById(long id);
}
