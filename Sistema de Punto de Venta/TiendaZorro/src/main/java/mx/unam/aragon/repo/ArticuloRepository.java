package mx.unam.aragon.repo;

import mx.unam.aragon.model.entity.AlmacenEntity;
import mx.unam.aragon.model.entity.ArticuloEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticuloRepository extends JpaRepository<ArticuloEntity, Long> {
}
