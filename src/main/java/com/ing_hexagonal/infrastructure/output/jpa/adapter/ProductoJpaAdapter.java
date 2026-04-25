package com.ing_hexagonal.infrastructure.output.jpa.adapter;

import com.ing_hexagonal.domain.model.ProductoModel;
import com.ing_hexagonal.domain.spi.IProductoPersistencePort;
import com.ing_hexagonal.infrastructure.output.jpa.entity.ProductoEntity;
import com.ing_hexagonal.infrastructure.output.jpa.repository.IProductoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adaptador que implementa el puerto de persistencia de productos.
 * Traduce entre Model del dominio y Entity de JPA.
 */
public class ProductoJpaAdapter implements IProductoPersistencePort {

    private final IProductoRepository productoRepository;

    public ProductoJpaAdapter(IProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    @Transactional
    public void guardarProducto(ProductoModel producto) {
        ProductoEntity entity = toEntity(producto);
        productoRepository.save(entity);
    }

    @Override
    @Transactional
    public void actualizarProducto(ProductoModel producto, Long id) {
        ProductoEntity entity = productoRepository.findById(id).orElseThrow();
        entity.setNombre(producto.getNombre());
        entity.setDescripcion(producto.getDescripcion());
        entity.setPrecio(producto.getPrecio());
        entity.setStock(producto.getStock());
        productoRepository.save(entity);
    }

    @Override
    @Transactional
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public Optional<ProductoModel> obtenerPorId(Long id) {
        return productoRepository.findById(id)
                .map(this::toModel);
    }

    @Override
    public List<ProductoModel> obtenerTodos() {
        return productoRepository.findAll()
                .stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existePorId(Long id) {
        return productoRepository.existsById(id);
    }

    /**
     * Convierte Entity a Model.
     */
    private ProductoModel toModel(ProductoEntity entity) {
        ProductoModel model = new ProductoModel();
        model.setId(entity.getId());
        model.setNombre(entity.getNombre());
        model.setDescripcion(entity.getDescripcion());
        model.setPrecio(entity.getPrecio());
        model.setStock(entity.getStock());
        return model;
    }

    /**
     * Convierte Model a Entity.
     */
    private ProductoEntity toEntity(ProductoModel model) {
        ProductoEntity entity = new ProductoEntity();
        entity.setId(model.getId());
        entity.setNombre(model.getNombre());
        entity.setDescripcion(model.getDescripcion());
        entity.setPrecio(model.getPrecio());
        entity.setStock(model.getStock());
        return entity;
    }
}
