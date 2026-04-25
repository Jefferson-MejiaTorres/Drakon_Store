package com.ing_hexagonal.domain.spi;

import com.ing_hexagonal.domain.model.ProductoModel;
import java.util.List;
import java.util.Optional;

/**
 * Puerto de salida que define qué necesita el dominio para persistir productos.
 * Abstrae la base de datos de detalles técnicos.
 */
public interface IProductoPersistencePort {

    /**
     * Guarda un nuevo producto.
     * @param producto Modelo del producto
     */
    void guardarProducto(ProductoModel producto);

    /**
     * Actualiza un producto existente.
     * @param producto Modelo del producto
     * @param id ID del producto
     */
    void actualizarProducto(ProductoModel producto, Long id);

    /**
     * Elimina un producto.
     * @param id ID del producto
     */
    void eliminarProducto(Long id);

    /**
     * Obtiene un producto por ID.
     * @param id ID del producto
     * @return Optional con el producto si existe
     */
    Optional<ProductoModel> obtenerPorId(Long id);

    /**
     * Obtiene todos los productos.
     * @return Lista de productos
     */
    List<ProductoModel> obtenerTodos();

    /**
     * Verifica si un producto existe.
     * @param id ID del producto
     * @return true si existe, false si no
     */
    boolean existePorId(Long id);
}
