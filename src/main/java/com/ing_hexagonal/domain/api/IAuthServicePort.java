package com.ing_hexagonal.domain.api;

/**
 * Puerto de entrada del dominio para las operaciones de autenticación.
 * Define los casos de uso: registro de usuarios e inicio de sesión.
 */
public interface IAuthServicePort {

    /**
     * Registra un nuevo usuario en el sistema.
     * @param nombre Nombre del usuario
     * @param correo Correo del usuario (único)
     * @param contrasena Contraseña
     */
    void registrarUsuario(String nombre, String correo, String contrasena);

    /**
     * Inicia sesión en el sistema.
     * @param correo Correo del usuario
     * @param contrasena Contraseña
     * @return Token JWT del usuario autenticado
     */
    String iniciarSesion(String correo, String contrasena);
}
