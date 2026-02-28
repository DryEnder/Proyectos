package mx.unam.aragon.service;

import mx.unam.aragon.model.entity.ClienteEntity;

import java.util.List;

public interface ClienteService {
    ClienteEntity save(ClienteEntity clienteEntity);
    List<ClienteEntity> findAll();
    void deleteById(long id);
    ClienteEntity findById(long id);

    ClienteEntity findByEmail(String email);
    ClienteEntity findByTelefono(String telefono);

    ClienteEntity findByTelefonoOrCorreo(String busqueda);
}
