package com.ing_hexagonal.domain.api;

import com.ing_hexagonal.domain.model.ProductoModel;
import java.util.List;

/**
 * Puerto de entrada del dominio para operaciones de productos.
 * Define los casos de uso de gestión de productos: crear, actualizar, eliminar y consultar.
 */
public interface IProductoServicePort {

    /**
     * Guarda un nuevo producto.
     * @param producto Modelo del producto a guardar
     */
    void guardarProducto(ProductoModel producto);

    /**
     * Actualiza un producto existente.
     * @param producto Modelo del producto con datos actualizados
     * @param id ID del producto a actualizar
     */
    void actualizarProducto(ProductoModel producto, Long id);

    /**
     * Elimina un producto.
     * @param id ID del producto a eliminar
     */
    void eliminarProducto(Long id);

    /**
     * Obtiene un producto por ID.
     * @param id ID del producto
     * @return Modelo del producto
     */
    ProductoModel obtenerProductoPorId(Long id);

    /**
     * Obtiene todos los productos.
     * @return Lista de productos
     */
    List<ProductoModel> obtenerTodosLosProductos();
}
