package mx.unam.aragon.repo;

import mx.unam.aragon.model.entity.ClienteEntity;
import mx.unam.aragon.model.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    Optional<ClienteEntity> findClienteEntityByEmail(String email);

    Optional<ClienteEntity> findClienteEntityByTelefono(String telefono);
}
