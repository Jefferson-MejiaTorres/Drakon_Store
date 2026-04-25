package com.ing_hexagonal.domain.spi;

import com.ing_hexagonal.domain.model.UsuarioModel;
import java.util.Optional;

/**
 * Puerto de salida que define qué necesita el dominio para persistir usuarios.
 * Abstrae la base de datos: puede ser MySQL, PostgreSQL, MongoDB, etc.
 */
public interface IUsuarioPersistencePort {

    /**
     * Guarda un nuevo usuario.
     * @param usuario Modelo del usuario
     */
    void guardarUsuario(UsuarioModel usuario);

    /**
     * Obtiene un usuario por correo.
     * @param correo Correo del usuario
     * @return Optional con el usuario si existe
     */
    Optional<UsuarioModel> obtenerPorCorreo(String correo);

    /**
     * Verifica si un correo ya existe.
     * @param correo Correo a verificar
     * @return true si existe, false si no
     */
    boolean existeCorreo(String correo);
}
