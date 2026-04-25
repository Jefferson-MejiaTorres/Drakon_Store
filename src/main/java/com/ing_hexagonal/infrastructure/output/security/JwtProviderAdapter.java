package com.ing_hexagonal.infrastructure.output.security;

import com.ing_hexagonal.domain.spi.IJwtProviderPort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

/**
 * Adaptador de salida que implementa el puerto encargado
 * de la gestión de tokens JWT usando jjwt.
 */
public class JwtProviderAdapter implements IJwtProviderPort {

    private static final String SECRET_KEY = "tu-clave-secreta-super-segura-minimo-32-caracteres";
    private static final long EXPIRATION_TIME = 86400000; // 24 horas en milisegundos
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    @Override
    public String generarToken(String correo) {
        Date ahora = new Date();
        Date expiracion = new Date(ahora.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(correo)
                .setIssuedAt(ahora)
                .setExpiration(expiracion)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String extraerCorreo(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
