package mx.unam.aragon.service.impl;

import mx.unam.aragon.model.entity.ArticuloEntity;
import mx.unam.aragon.model.entity.CategoriaEntity;
import mx.unam.aragon.model.entity.ProveedorEntity;
import mx.unam.aragon.service.ArticuloService;
import mx.unam.aragon.service.CategoriaService;
import mx.unam.aragon.service.ProveedorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticuloServiceImplTest {
    @Autowired
    private ArticuloService articuloService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProveedorService proveedorService;

    @Test
    void save() {
        CategoriaEntity catego = categoriaService.findById(1L);
        ProveedorEntity proveedor = proveedorService.findById(1L);
        ArticuloEntity articulo = ArticuloEntity.builder()
                .categoria(catego)
                .proveedor(proveedor)
                .codigo("009389874")
                .nombre("aceite")
                .descripcion("pinche aceite feo")
                .urlFoto("/aceite.jps")
                .precioVenta(29.60F)
                .build();
        articuloService.save(articulo);
    }

    @Test
    void findAll() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void findById() {
    }
}