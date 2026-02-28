package mx.unam.aragon.repo;

import mx.unam.aragon.model.entity.DetalleIngresoEntity;
import mx.unam.aragon.model.entity.IngresoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngresoRepository extends JpaRepository<IngresoEntity, Long> {
}
