package mx.unam.aragon.service.impl;

import jakarta.transaction.Transactional;
import mx.unam.aragon.model.entity.VentaEntity;
import mx.unam.aragon.repo.VentaRepository;
import mx.unam.aragon.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VentaServiceImpl implements VentaService {
    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public VentaEntity save(VentaEntity ventaEntity) {
        return ventaRepository.save(ventaEntity);
    }

    @Override
    public List<VentaEntity> findAll() {
        return ventaRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        ventaRepository.deleteById(id);
    }

    @Override
    public VentaEntity findById(long id) {
        Optional<VentaEntity> ventaEntity = ventaRepository.findById(id);
        return ventaEntity.orElse(null);
    }
}
