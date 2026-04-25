package com.ing_hexagonal.infrastructure.configuration;

import com.ing_hexagonal.domain.api.IAuthServicePort;
import com.ing_hexagonal.domain.api.IProductoServicePort;
import com.ing_hexagonal.domain.spi.IPasswordEncoderPort;
import com.ing_hexagonal.domain.spi.IJwtProviderPort;
import com.ing_hexagonal.domain.spi.IProductoPersistencePort;
import com.ing_hexagonal.domain.spi.IUsuarioPersistencePort;
import com.ing_hexagonal.domain.usecase.AuthUseCase;
import com.ing_hexagonal.domain.usecase.ProductoUseCase;
import com.ing_hexagonal.infrastructure.output.jpa.adapter.PasswordEncoderAdapter;
import com.ing_hexagonal.infrastructure.output.jpa.adapter.ProductoJpaAdapter;
import com.ing_hexagonal.infrastructure.output.jpa.adapter.UsuarioJpaAdapter;
import com.ing_hexagonal.infrastructure.output.security.CustomUserDetailsService;
import com.ing_hexagonal.infrastructure.output.security.JwtProviderAdapter;
import com.ing_hexagonal.infrastructure.output.jpa.repository.IProductoRepository;
import com.ing_hexagonal.infrastructure.output.jpa.repository.IUsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Clase de configuración encargada de registrar los casos de uso
 * y ensamblar las dependencias entre puertos y adaptadores.
 * Su función dentro de la arquitectura hexagonal es conectar
 * el dominio con las implementaciones de infraestructura
 * sin que el núcleo del sistema dependa de Spring directamente.
 */

@Configuration
public class BeanConfiguration {

    @Bean
    public IPasswordEncoderPort passwordEncoderPort() {
        return new PasswordEncoderAdapter();
    }

    @Bean
    public IJwtProviderPort jwtProviderPort() {
        return new JwtProviderAdapter();
    }

    @Bean
    public IUsuarioPersistencePort usuarioPersistencePort(IUsuarioRepository usuarioRepository) {
        return new UsuarioJpaAdapter(usuarioRepository);
    }

    @Bean
    public IProductoPersistencePort productoPersistencePort(IProductoRepository productoRepository) {
        return new ProductoJpaAdapter(productoRepository);
    }

    @Bean
    public UserDetailsService userDetailsService(IUsuarioPersistencePort usuarioPersistencePort) {
        return new CustomUserDetailsService(usuarioPersistencePort);
    }

    @Bean
    public IAuthServicePort authServicePort(IUsuarioPersistencePort usuarioPersistencePort,
                                            IPasswordEncoderPort passwordEncoderPort,
                                            IJwtProviderPort jwtProviderPort) {
        return new AuthUseCase(usuarioPersistencePort, passwordEncoderPort, jwtProviderPort);
    }

    @Bean
    public IProductoServicePort productoServicePort(IProductoPersistencePort productoPersistencePort) {
        return new ProductoUseCase(productoPersistencePort);
    }
}