package mx.unam.aragon.service.impl;

import mx.unam.aragon.model.entity.ClienteEntity;
import mx.unam.aragon.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClienteServiceImplTest {
    @Autowired
    ClienteService clienteService;

    @Test
    void save() {
        ClienteEntity cliente = ClienteEntity.builder()
                .nombre("Test")
                .email("Test@email.com")
                .telefono("123456")

                .build();
        clienteService.save(cliente);
    }

    @Test
    void findAll() {
        List<ClienteEntity> list = clienteService.findAll();
        System.out.println(list);
    }

    @Test
    void findById() {
        ClienteEntity cliente = clienteService.findById(1L);
        System.out.println(cliente);
    }

    @Test
    void findByEmail() {
        ClienteEntity cliente = clienteService.findByEmail("Test@email.com");
        System.out.println(cliente);
    }

    @Test
    void findByTelefono() {
        ClienteEntity cliente = clienteService.findByTelefono("123456");
        System.out.println(cliente);
    }
}