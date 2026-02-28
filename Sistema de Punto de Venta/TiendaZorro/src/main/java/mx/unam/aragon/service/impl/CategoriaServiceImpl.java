package mx.unam.aragon.service.impl;

import jakarta.transaction.Transactional;
import mx.unam.aragon.model.entity.CategoriaEntity;
import mx.unam.aragon.repo.CategoriaRepository;
import mx.unam.aragon.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public CategoriaEntity save(CategoriaEntity categoriaEntity) {
        return categoriaRepository.save(categoriaEntity);
    }

    @Override
    @Transactional
    public List<CategoriaEntity> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        categoriaRepository.deleteById(id);
    }

    @Override
    public CategoriaEntity findById(long id) {
        Optional<CategoriaEntity> categoriaEntity = categoriaRepository.findById(id);
        return categoriaEntity.orElse(null);
    }
}
