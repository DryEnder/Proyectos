package mx.unam.aragon.repo;

import mx.unam.aragon.model.entity.AlmacenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlmacenRepository extends JpaRepository<AlmacenEntity, Long> {
}
