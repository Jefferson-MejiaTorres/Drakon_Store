package com.ing_hexagonal.application.dto.request;

import java.math.BigDecimal;

/**
 * DTO para crear o actualizar productos.
 */
public class ProductoRequestDto {

    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer stock;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
