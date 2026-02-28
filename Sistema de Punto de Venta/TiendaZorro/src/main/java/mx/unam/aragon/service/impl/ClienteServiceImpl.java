package mx.unam.aragon.service.impl;

import jakarta.transaction.Transactional;
import mx.unam.aragon.model.entity.ClienteEntity;
import mx.unam.aragon.repo.ClienteRepository;
import mx.unam.aragon.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public ClienteEntity save(ClienteEntity clienteEntity) {
        return clienteRepository.save(clienteEntity);
    }

    @Override
    public List<ClienteEntity> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public ClienteEntity findById(long id) {
        Optional<ClienteEntity> clienteEntity = clienteRepository.findById(id);
        return clienteEntity.orElse(null);
    }

    @Override
    public ClienteEntity findByEmail(String email) {
        Optional<ClienteEntity> clienteEntity = clienteRepository.findClienteEntityByEmail(email);
        return clienteEntity.orElse(null);
    }

    @Override
    public ClienteEntity findByTelefono(String telefono) {
        Optional<ClienteEntity> clienteEntity = clienteRepository.findClienteEntityByTelefono(telefono);
        return clienteEntity.orElse(null);
    }

    @Override
    public ClienteEntity findByTelefonoOrCorreo(String busqueda) {
        return clienteRepository.findClienteEntityByTelefono(busqueda)
                .orElse(clienteRepository.findClienteEntityByEmail(busqueda).orElse(null));
    }
}
