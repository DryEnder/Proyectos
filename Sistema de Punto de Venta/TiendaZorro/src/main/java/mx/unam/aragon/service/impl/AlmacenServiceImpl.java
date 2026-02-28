package mx.unam.aragon.service.impl;

import jakarta.transaction.Transactional;
import mx.unam.aragon.model.entity.AlmacenEntity;
import mx.unam.aragon.repo.AlmacenRepository;
import mx.unam.aragon.service.AlmacenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AlmacenServiceImpl implements AlmacenService {
    @Autowired
    private AlmacenRepository almacenRepository;

    @Override
    public AlmacenEntity save(AlmacenEntity almacenEntity) {
        return almacenRepository.save(almacenEntity);
    }

    @Override
    public List<AlmacenEntity> findAll() {
        return almacenRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        almacenRepository.deleteById(id);
    }

    @Override
    public AlmacenEntity findById(long id) {
        Optional<AlmacenEntity> almacenEntity = almacenRepository.findById(id);
        return almacenEntity.orElse(null);
    }
}
