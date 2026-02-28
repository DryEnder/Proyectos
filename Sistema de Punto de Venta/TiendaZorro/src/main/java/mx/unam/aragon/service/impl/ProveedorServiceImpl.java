package mx.unam.aragon.service.impl;

import jakarta.transaction.Transactional;
import mx.unam.aragon.model.entity.ProveedorEntity;
import mx.unam.aragon.repo.ProveedorRepository;
import mx.unam.aragon.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProveedorServiceImpl implements ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public ProveedorEntity save(ProveedorEntity proveedorEntity) {
        return proveedorRepository.save(proveedorEntity);
    }

    @Override
    public List<ProveedorEntity> findAll() {
        return proveedorRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        proveedorRepository.deleteById(id);
    }

    @Override
    public ProveedorEntity findById(long id) {
        Optional<ProveedorEntity> proveedorEntity = proveedorRepository.findById(id);
        return proveedorEntity.orElse(null);
    }
}
