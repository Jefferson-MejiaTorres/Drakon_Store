package com.ing_hexagonal.infrastructure.output.jpa.repository;

import com.ing_hexagonal.infrastructure.output.jpa.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio JPA para usuarios.
 * Spring Data proporciona CRUD automático.
 */
@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    /**
     * Busca un usuario por correo.
     */
    Optional<UsuarioEntity> findByCorreo(String correo);

    /**
     * Verifica si existe un usuario con el correo especificado.
     */
    boolean existsByCorreo(String correo);
}
