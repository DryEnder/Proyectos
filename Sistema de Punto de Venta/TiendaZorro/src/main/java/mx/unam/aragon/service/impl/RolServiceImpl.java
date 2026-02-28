package mx.unam.aragon.service.impl;

import jakarta.transaction.Transactional;
import mx.unam.aragon.model.entity.RolEntity;
import mx.unam.aragon.repo.RolRepository;
import mx.unam.aragon.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RolServiceImpl implements RolService {
    @Autowired
    private RolRepository rolRepository;

    @Override
    public RolEntity save(RolEntity rolEntity) {
        return rolRepository.save(rolEntity);
    }

    @Override
    public List<RolEntity> findAll() {
        return rolRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        rolRepository.deleteById(id);
    }

    @Override
    public RolEntity findById(long id) {
        Optional<RolEntity> rolEntity = rolRepository.findById(id);
        return rolEntity.orElse(null);
    }
}
