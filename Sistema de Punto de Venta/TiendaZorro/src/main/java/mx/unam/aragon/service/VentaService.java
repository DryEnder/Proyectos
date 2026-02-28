package mx.unam.aragon.service;

import mx.unam.aragon.model.entity.VentaEntity;

import java.util.List;

public interface VentaService {
    VentaEntity save(VentaEntity ventaEntity);
    List<VentaEntity> findAll();
    void deleteById(long id);
    VentaEntity findById(long id);
}
