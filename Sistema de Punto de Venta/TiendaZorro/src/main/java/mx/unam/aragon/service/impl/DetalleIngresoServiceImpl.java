package mx.unam.aragon.service.impl;

import jakarta.transaction.Transactional;
import mx.unam.aragon.model.entity.DetalleIngresoEntity;
import mx.unam.aragon.repo.DetalleIngresoRepository;
import mx.unam.aragon.service.DetalleIngresoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DetalleIngresoServiceImpl implements DetalleIngresoService {
    @Autowired
    private DetalleIngresoRepository detalleIngresoRepository;

    @Override
    public DetalleIngresoEntity save(DetalleIngresoEntity detalleIngresoEntity) {
        return detalleIngresoRepository.save(detalleIngresoEntity);
    }

    @Override
    public List<DetalleIngresoEntity> findAll() {
        return detalleIngresoRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        detalleIngresoRepository.deleteById(id);
    }

    @Override
    public DetalleIngresoEntity findById(long id) {
        Optional<DetalleIngresoEntity> detalleIngresoEntity = detalleIngresoRepository.findById(id);
        return detalleIngresoEntity.orElse(null);
    }
}
