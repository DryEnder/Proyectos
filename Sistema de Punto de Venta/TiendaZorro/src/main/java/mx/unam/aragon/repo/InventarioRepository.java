package mx.unam.aragon.repo;

import mx.unam.aragon.model.entity.IdInventario;
import mx.unam.aragon.model.entity.InventarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<InventarioEntity, IdInventario> {
}
