package mx.unam.aragon.service.impl;

import jakarta.transaction.Transactional;
import mx.unam.aragon.model.entity.ArticuloEntity;
import mx.unam.aragon.model.entity.DetalleVentaEntity;
import mx.unam.aragon.model.entity.VentaEntity;
import mx.unam.aragon.repo.DetalleVentaRepository;
import mx.unam.aragon.service.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DetalleVentaServiceImpl implements DetalleVentaService {
    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Override
    public DetalleVentaEntity save(DetalleVentaEntity detalleVentaEntity) {
        return detalleVentaRepository.save(detalleVentaEntity);
    }

    @Override
    public List<DetalleVentaEntity> findAll() {
        return detalleVentaRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        detalleVentaRepository.deleteById(id);
    }

    @Override
    public DetalleVentaEntity findById(long id) {
        Optional<DetalleVentaEntity> detalleVentaEntity = detalleVentaRepository.findById(id);
        return detalleVentaEntity.orElse(null);
    }

    @Override
    public List<DetalleVentaEntity> findByVentaId(long id) {
        return detalleVentaRepository.findAllByVenta_Id(id);
    }


    @Override
    public DetalleVentaEntity findByVentaAndArticulo(VentaEntity venta, ArticuloEntity articulo) {
        return detalleVentaRepository.findByVentaAndArticulo(venta, articulo).orElse(null);
    }

    @Override
    public void deleteByVenta(VentaEntity venta) {
    }


}
