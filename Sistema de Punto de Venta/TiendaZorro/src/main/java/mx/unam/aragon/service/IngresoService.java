package mx.unam.aragon.service;

import mx.unam.aragon.model.entity.IngresoEntity;

import java.util.List;

public interface IngresoService {
    IngresoEntity save(IngresoEntity ingresoEntity);
    List<IngresoEntity> findAll();
    void deleteById(long id);
    IngresoEntity findById(long id);
}
