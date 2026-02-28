package mx.unam.aragon.repo;

import mx.unam.aragon.model.entity.CategoriaEntity;
import mx.unam.aragon.model.entity.DetalleIngresoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleIngresoRepository extends JpaRepository<DetalleIngresoEntity, Long> {
}
