package com.ing_hexagonal.infrastructure.output.security;

import com.ing_hexagonal.domain.spi.IJwtProviderPort;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro de seguridad que intercepta cada petición HTTP
 * para verificar si contiene un token JWT válido.
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final IJwtProviderPort jwtProviderPort;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(IJwtProviderPort jwtProviderPort, UserDetailsService userDetailsService) {
        this.jwtProviderPort = jwtProviderPort;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String authorizationHeader = request.getHeader("Authorization");
        
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            
            if (jwtProviderPort.validarToken(token)) {
                String correo = jwtProviderPort.extraerCorreo(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(correo);
                
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        
        filterChain.doFilter(request, response);
    }
}
