package mx.unam.aragon.service;

import mx.unam.aragon.model.entity.ArticuloEntity;

import java.util.List;

public interface ArticuloService {
    ArticuloEntity save(ArticuloEntity articuloEntity);
    List<ArticuloEntity> findAll();
    void deleteById(long id);
    ArticuloEntity findById(long id);
}
