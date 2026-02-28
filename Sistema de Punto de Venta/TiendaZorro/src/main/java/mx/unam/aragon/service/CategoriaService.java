package mx.unam.aragon.service;

import mx.unam.aragon.model.entity.AlmacenEntity;
import mx.unam.aragon.model.entity.CategoriaEntity;

import java.util.List;

public interface CategoriaService {
    CategoriaEntity save(CategoriaEntity categoriaEntity);
    List<CategoriaEntity> findAll();
    void deleteById(long id);
    CategoriaEntity findById(long id);
}
