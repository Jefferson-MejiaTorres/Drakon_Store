package com.ing_hexagonal.domain.spi;

/**
 * Puerto de salida para encriptación de contraseñas.
 * Abstrae la implementación técnica de cifrado (BCrypt, etc).
 */
public interface IPasswordEncoderPort {

    /**
     * Encripta una contraseña.
     * @param contrasena Contraseña en texto plano
     * @return Contraseña encriptada
     */
    String encriptar(String contrasena);

    /**
     * Verifica si una contraseña coincide con su versión encriptada.
     * @param contrasena Contraseña en texto plano
     * @param encriptada Contraseña encriptada
     * @return true si coinciden, false si no
     */
    boolean coincidir(String contrasena, String encriptada);
}
