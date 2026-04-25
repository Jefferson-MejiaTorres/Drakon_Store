package com.ing_hexagonal.application.dto.request;

/**
 * Objeto de transferencia de datos utilizado para recibir la información
 * necesaria en el proceso de registro de un nuevo usuario.
 */
public class RegisterRequestDto {

    private String nombre;
    private String correo;
    private String contrasena;

    public RegisterRequestDto() {
    }

    public RegisterRequestDto(String nombre, String correo, String contrasena) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
