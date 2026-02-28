package mx.unam.aragon.service;

import mx.unam.aragon.model.entity.ProveedorEntity;

import java.util.List;

public interface ProveedorService {
    ProveedorEntity save(ProveedorEntity proveedorEntity);
    List<ProveedorEntity> findAll();
    void deleteById(long id);
    ProveedorEntity findById(long id);
}
