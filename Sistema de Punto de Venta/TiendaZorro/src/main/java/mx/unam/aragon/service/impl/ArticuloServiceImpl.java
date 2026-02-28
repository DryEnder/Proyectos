package mx.unam.aragon.service.impl;

import jakarta.transaction.Transactional;
import mx.unam.aragon.model.entity.ArticuloEntity;
import mx.unam.aragon.repo.ArticuloRepository;
import mx.unam.aragon.service.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ArticuloServiceImpl implements ArticuloService {
    @Autowired
    private ArticuloRepository articuloRepository;

    @Override
    public ArticuloEntity save(ArticuloEntity articuloEntity) {
        return articuloRepository.save(articuloEntity);
    }

    @Override
    @Transactional
    public List<ArticuloEntity> findAll() {
        return articuloRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        articuloRepository.deleteById(id);
    }

    @Override
    public ArticuloEntity findById(long id) {
        Optional<ArticuloEntity> articuloEntity = articuloRepository.findById(id);
        return articuloEntity.orElse(null);
    }
}
