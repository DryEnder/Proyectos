package mx.unam.aragon.service.impl;

import mx.unam.aragon.model.entity.AlmacenEntity;
import mx.unam.aragon.model.entity.ArticuloEntity;
import mx.unam.aragon.model.entity.IdInventario;
import mx.unam.aragon.model.entity.InventarioEntity;
import mx.unam.aragon.service.AlmacenService;
import mx.unam.aragon.service.ArticuloService;
import mx.unam.aragon.service.InventarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InventarioServiceImplTest {
    @Autowired
    private InventarioService service;
    @Autowired
    private ArticuloService articuloService;
    @Autowired
    private AlmacenService almacenService;
    @Autowired
    private InventarioService inventarioService;


    @Test
    void save() {
        ArticuloEntity articulo = articuloService.findById(2L);
        AlmacenEntity almacen = almacenService.findById(3L);
        InventarioEntity inventario = InventarioEntity.builder()
                .idInventario(new IdInventario(articulo,almacen))
                .stock(1000)
                .build();
        service.save(inventario);
    }

    @Test
    void findAll() {
        List<InventarioEntity> list = service.findAll();
        System.out.println(list);
    }

    @Test
    void findById() {
        InventarioEntity inventario = inventarioService.findById(new IdInventario(articuloService.findById(2L),almacenService.findById(3L)));
        System.out.println(inventario);
        System.out.println("articulo " + inventario.getIdInventario().getArticulo().getNombre());
        System.out.println("categoria " + inventario.getIdInventario().getArticulo().getCategoria().getNombre());
        System.out.println("almacen " + inventario.getIdInventario().getAlmacen().getNombre());
        System.out.println("stock " + inventario.getStock());
        LocalDate fechaInicial = LocalDate.now();
        System.out.println("fechaInicial " + fechaInicial);
    }

    @Test
    void deleteById() {
    }

    void actualizar() {
        InventarioEntity inventario = inventarioService.findById(new IdInventario(articuloService.findById(2L),almacenService.findById(3L)));
        System.out.println(inventario);
        System.out.println("articulo " + inventario.getIdInventario().getArticulo().getNombre());
        System.out.println("categoria " + inventario.getIdInventario().getArticulo().getCategoria().getNombre());
        System.out.println("almacen " + inventario.getIdInventario().getAlmacen().getNombre());
        System.out.println("stock " + inventario.getStock());
        LocalDate fechaInicial = LocalDate.now();
        System.out.println("fechaInicial " + fechaInicial);

        inventario.setStock(inventario.getStock()-1);
        service.save(inventario);
    }

}