package mx.unam.aragon.service.impl;

import mx.unam.aragon.model.entity.ArticuloEntity;
import mx.unam.aragon.model.entity.DetalleVentaEntity;
import mx.unam.aragon.model.entity.VentaEntity;
import mx.unam.aragon.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DetalleVentaServiceImplTest {
    @Autowired
    private DetalleVentaService detalleVentaService;
    @Autowired
    private ClienteService clienteService;
    //@Autowired
    //private UsuarioService usuarioService;
    @Autowired
    private AlmacenService almacenService;
    @Autowired
    private ArticuloService articuloService;
    @Autowired
    private VentaService ventaService;

    @Test
    void save() {
        VentaEntity venta = VentaEntity.builder()
                .numComprobante("1234")
                .fechaHora(LocalDateTime.now())
                .cliente(clienteService.findById(1L))
                //.usuario(usuarioService.findById(1L))
                .almacen(almacenService.findById(1L))
                .total(0)
                .build();
        ventaService.save(venta);
        List<ArticuloEntity> arts = articuloService.findAll();
        DetalleVentaEntity detalle = DetalleVentaEntity.builder()
                .venta(venta)
                .articulo(arts.get(1))
                .cantidad((int) (arts.get(1).getPrecioVenta()*2))
                .build();
        detalleVentaService.save(detalle);
        DetalleVentaEntity detalle2 = DetalleVentaEntity.builder()
                .venta(venta)
                .articulo(arts.get(2))
                .cantidad((int) (arts.get(2).getPrecioVenta()*3))
                .build();
        detalleVentaService.save(detalle2);
        DetalleVentaEntity detalle3 = DetalleVentaEntity.builder()
                .venta(venta)
                .articulo(arts.get(2))
                .cantidad((int) (arts.get(2).getPrecioVenta()*6))
                .build();
        detalleVentaService.save(detalle3);
        System.out.println(venta.getId());
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByVentaId() {
        List<DetalleVentaEntity> list = detalleVentaService.findByVentaId(255);
        System.out.println(list);
    }


}