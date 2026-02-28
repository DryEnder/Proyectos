package mx.unam.aragon.service.impl;

import mx.unam.aragon.model.entity.AlmacenEntity;
import mx.unam.aragon.service.AlmacenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AlmacenServiceImplTest {
    @Autowired
    private AlmacenService service;

    @Test
    void save() {
        AlmacenEntity entity = AlmacenEntity.builder()
                .nombre("Almacen Chido")
                .descripcion("Almacen Chido")
                .build();
        service.save(entity);
    }

    @Test
    void findAll() {
        List<AlmacenEntity> list = service.findAll();
        System.out.println(list);
    }

    @Test
    void deleteById() {
        service.deleteById(4L);
    }

    @Test
    void findById() {
        AlmacenEntity entity = service.findById(1L);
        System.out.println(entity);
    }
}