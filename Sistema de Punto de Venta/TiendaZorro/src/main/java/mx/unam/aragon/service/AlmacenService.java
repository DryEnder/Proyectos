package mx.unam.aragon.service;

import mx.unam.aragon.model.entity.AlmacenEntity;

import java.util.List;

public interface AlmacenService {
    AlmacenEntity save(AlmacenEntity almacenEntity);
    List<AlmacenEntity> findAll();
    void deleteById(long id);
    AlmacenEntity findById(long id);
}
