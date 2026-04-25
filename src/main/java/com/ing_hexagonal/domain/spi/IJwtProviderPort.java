package com.ing_hexagonal.domain.spi;

/**
 * Puerto de salida para gestión de tokens JWT.
 * Abstrae la generación y validación de tokens sin depender de librerías externas.
 */
public interface IJwtProviderPort {

    /**
     * Genera un token JWT para un usuario.
     * @param correo Correo del usuario
     * @return Token JWT
     */
    String generarToken(String correo);

    /**
     * Valida un token JWT.
     * @param token Token a validar
     * @return true si es válido, false si no
     */
    boolean validarToken(String token);

    /**
     * Extrae el correo de un token JWT.
     * @param token Token del que extraer el correo
     * @return Correo del usuario
     */
    String extraerCorreo(String token);
}
