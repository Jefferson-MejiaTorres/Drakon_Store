package com.ing_hexagonal.application.dto.response;

/**
 * DTO para respuesta de autenticación.
 * Contiene el token JWT generado.
 */
public class AuthResponseDto {

    private String token;
    private String tipoToken = "Bearer";

    public AuthResponseDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipoToken() {
        return tipoToken;
    }

    public void setTipoToken(String tipoToken) {
        this.tipoToken = tipoToken;
    }
}
