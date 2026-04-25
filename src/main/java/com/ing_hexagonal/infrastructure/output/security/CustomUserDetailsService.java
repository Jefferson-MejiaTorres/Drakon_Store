package com.ing_hexagonal.infrastructure.output.security;

import com.ing_hexagonal.domain.model.UsuarioModel;
import com.ing_hexagonal.domain.spi.IUsuarioPersistencePort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Servicio de infraestructura requerido por Spring Security
 * para cargar la información de un usuario durante el proceso
 * de autenticación.
 */
public class CustomUserDetailsService implements UserDetailsService {

    private final IUsuarioPersistencePort usuarioPersistencePort;

    public CustomUserDetailsService(IUsuarioPersistencePort usuarioPersistencePort) {
        this.usuarioPersistencePort = usuarioPersistencePort;
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        UsuarioModel usuario = usuarioPersistencePort.obtenerPorCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con correo: " + correo));

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().name()));

        return User.builder()
                .username(usuario.getCorreo())
                .password(usuario.getContrasena())
                .authorities(authorities)
                .accountLocked(!usuario.isActivo())
                .build();
    }
}
