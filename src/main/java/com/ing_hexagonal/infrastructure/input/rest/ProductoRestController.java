package com.ing_hexagonal.infrastructure.input.rest;

import com.ing_hexagonal.application.dto.request.ProductoRequestDto;
import com.ing_hexagonal.application.dto.response.ProductoResponseDto;
import com.ing_hexagonal.application.mapper.ProductoDtoMapper;
import com.ing_hexagonal.domain.api.IProductoServicePort;
import com.ing_hexagonal.domain.model.ProductoModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestión de productos.
 * Expone los endpoints para crear, actualizar, eliminar y obtener productos.
 */
@RestController
@RequestMapping("/productos")
public class ProductoRestController {

    private final IProductoServicePort productoServicePort;

    public ProductoRestController(IProductoServicePort productoServicePort) {
        this.productoServicePort = productoServicePort;
    }

    @PostMapping
    public ResponseEntity<String> crearProducto(@RequestBody ProductoRequestDto request) {
        ProductoModel producto = ProductoDtoMapper.toModel(request);
        productoServicePort.guardarProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Producto creado exitosamente");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDto> obtenerProductoPorId(@PathVariable Long id) {
        ProductoModel producto = productoServicePort.obtenerProductoPorId(id);
        return ResponseEntity.ok(ProductoDtoMapper.toResponse(producto));
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponseDto>> obtenerTodosLosProductos() {
        List<ProductoModel> productos = productoServicePort.obtenerTodosLosProductos();
        return ResponseEntity.ok(ProductoDtoMapper.toResponseList(productos));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarProducto(
            @PathVariable Long id,
            @RequestBody ProductoRequestDto request) {
        ProductoModel producto = ProductoDtoMapper.toModel(request);
        productoServicePort.actualizarProducto(producto, id);
        return ResponseEntity.ok("Producto actualizado exitosamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id) {
        productoServicePort.eliminarProducto(id);
        return ResponseEntity.ok("Producto eliminado exitosamente");
    }
}
