package mx.unam.aragon.repo;

import mx.unam.aragon.model.entity.RolEntity;
import mx.unam.aragon.model.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByEmail(String username);
}
