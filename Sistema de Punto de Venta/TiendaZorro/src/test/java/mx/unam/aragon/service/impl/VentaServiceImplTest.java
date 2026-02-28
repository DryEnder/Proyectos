package mx.unam.aragon.service.impl;

import mx.unam.aragon.model.entity.VentaEntity;
import mx.unam.aragon.service.VentaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VentaServiceImplTest {
    @Autowired
    private VentaService service;
    @Autowired
    private VentaService ventaService;

    @Test
    void save() {
    }

    @Test
    void findAll() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void findById() {
        VentaEntity ve = ventaService.findById(1L);
        System.out.println(ve);
        System.out.println(ve.getCliente().getNombre());

    }
}