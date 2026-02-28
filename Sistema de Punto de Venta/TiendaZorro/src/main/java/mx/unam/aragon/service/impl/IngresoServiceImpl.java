package mx.unam.aragon.service.impl;

import jakarta.transaction.Transactional;
import mx.unam.aragon.model.entity.IngresoEntity;
import mx.unam.aragon.repo.IngresoRepository;
import mx.unam.aragon.service.IngresoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IngresoServiceImpl implements IngresoService {
    @Autowired
    private IngresoRepository ingresoRepository;

    @Override
    public IngresoEntity save(IngresoEntity ingresoEntity) {
        return ingresoRepository.save(ingresoEntity);
    }

    @Override
    public List<IngresoEntity> findAll() {
        return ingresoRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        ingresoRepository.deleteById(id);
    }

    @Override
    public IngresoEntity findById(long id) {
        Optional<IngresoEntity> ingresoEntity = ingresoRepository.findById(id);
        return ingresoEntity.orElse(null);
    }
}
