package mx.unam.aragon.repo;

import mx.unam.aragon.model.entity.DetalleIngresoEntity;
import mx.unam.aragon.model.entity.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepository extends JpaRepository<ProveedorEntity, Long> {
}
