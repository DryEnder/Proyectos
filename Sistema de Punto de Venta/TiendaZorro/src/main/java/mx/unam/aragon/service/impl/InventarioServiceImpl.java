package mx.unam.aragon.service.impl;

import jakarta.transaction.Transactional;
import mx.unam.aragon.model.entity.IdInventario;
import mx.unam.aragon.model.entity.InventarioEntity;
import mx.unam.aragon.repo.InventarioRepository;
import mx.unam.aragon.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InventarioServiceImpl implements InventarioService {
    @Autowired
    private InventarioRepository inventarioRepository;

    @Override
    public InventarioEntity save(InventarioEntity inventario) {
        return inventarioRepository.save(inventario);
    }

    @Override
    public List<InventarioEntity> findAll() {
        return inventarioRepository.findAll();
    }

    @Override
    public InventarioEntity findById(IdInventario idi) {
        Optional<InventarioEntity> inventario = inventarioRepository.findById(idi);
        return inventario.orElse(null);
    }

    @Override
    public void deleteById(IdInventario idi) {
        inventarioRepository.deleteById(idi);
    }
}
