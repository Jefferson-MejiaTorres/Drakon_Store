package com.ing_hexagonal.infrastructure.output.jpa.repository;

import com.ing_hexagonal.infrastructure.output.jpa.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para productos.
 */
@Repository
public interface IProductoRepository extends JpaRepository<ProductoEntity, Long> {
}
