package mx.unam.aragon.service;

import mx.unam.aragon.model.entity.RolEntity;

import java.util.List;

public interface RolService {
    RolEntity save(RolEntity rolEntity);
    List<RolEntity> findAll();
    void deleteById(long id);
    RolEntity findById(long id);
}
