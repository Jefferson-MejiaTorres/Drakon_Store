package com.ing_hexagonal.infrastructure.output.jpa.adapter;

import com.ing_hexagonal.domain.spi.IPasswordEncoderPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Adaptador de salida que implementa el puerto de codificación
 * de contraseñas usando Spring Security BCrypt.
 */
public class PasswordEncoderAdapter implements IPasswordEncoderPort {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String encriptar(String contrasena) {
        return passwordEncoder.encode(contrasena);
    }

    @Override
    public boolean coincidir(String contrasena, String encriptada) {
        return passwordEncoder.matches(contrasena, encriptada);
    }
}
