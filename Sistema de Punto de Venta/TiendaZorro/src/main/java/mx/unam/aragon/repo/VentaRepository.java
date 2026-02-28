package mx.unam.aragon.repo;

import mx.unam.aragon.model.entity.UsuarioEntity;
import mx.unam.aragon.model.entity.VentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<VentaEntity, Long> {
}
