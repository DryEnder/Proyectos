package mx.unam.aragon.service;

import mx.unam.aragon.model.entity.UsuarioEntity;

import java.util.List;

public interface UsuarioService {
    UsuarioEntity save(UsuarioEntity usuarioEntity);
    List<UsuarioEntity> findAll();
    void deleteById(long id);
    UsuarioEntity findById(long id);
}
