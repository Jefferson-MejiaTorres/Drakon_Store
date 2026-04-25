package com.ing_hexagonal.domain.usecase;

import com.ing_hexagonal.domain.api.IProductoServicePort;
import com.ing_hexagonal.domain.exception.DomainException;
import com.ing_hexagonal.domain.model.ProductoModel;
import com.ing_hexagonal.domain.spi.IProductoPersistencePort;

import java.math.BigDecimal;
import java.util.List;

/**
 * Caso de uso de productos.
 * Implementa la lógica de negocio: CRUD y validaciones de productos.
 */
public class ProductoUseCase implements IProductoServicePort {

    private final IProductoPersistencePort productoPersistencePort;

    public ProductoUseCase(IProductoPersistencePort productoPersistencePort) {
        this.productoPersistencePort = productoPersistencePort;
    }

    @Override
    public void guardarProducto(ProductoModel producto) {
        validarProducto(producto);

        productoPersistencePort.guardarProducto(producto);
    }

    @Override
    public void actualizarProducto(ProductoModel producto, Long id) {
        if (id == null) {
            throw new DomainException("El ID del producto es obligatorio");
        }

        if (!productoPersistencePort.existePorId(id)) {
            throw new DomainException("El producto no existe");
        }

        validarProducto(producto);

        productoPersistencePort.actualizarProducto(producto, id);
    }

    @Override
    public void eliminarProducto(Long id) {
        if (id == null) {
            throw new DomainException("El ID del producto es obligatorio");
        }

        if (!productoPersistencePort.existePorId(id)) {
            throw new DomainException("El producto no existe");
        }

        productoPersistencePort.eliminarProducto(id);
    }

    @Override
    public ProductoModel obtenerProductoPorId(Long id) {
        if (id == null) {
            throw new DomainException("El ID del producto es obligatorio");
        }

        return productoPersistencePort.obtenerPorId(id)
                .orElseThrow(() -> new DomainException("El producto no existe"));
    }

    @Override
    public List<ProductoModel> obtenerTodosLosProductos() {
        return productoPersistencePort.obtenerTodos();
    }

    /**
     * Valida los datos del producto.
     */
    private void validarProducto(ProductoModel producto) {
        if (producto.getNombre() == null || producto.getNombre().isBlank()) {
            throw new DomainException("El nombre del producto es obligatorio");
        }

        if (producto.getPrecio() == null || producto.getPrecio().compareTo(BigDecimal.ZERO) <= 0) {
            throw new DomainException("El precio debe ser mayor a cero");
        }

        if (producto.getStock() == null || producto.getStock() < 0) {
            throw new DomainException("El stock no puede ser negativo");
        }
    }
}
