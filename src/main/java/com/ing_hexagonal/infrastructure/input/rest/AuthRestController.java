package com.ing_hexagonal.infrastructure.input.rest;

import com.ing_hexagonal.application.dto.request.LoginRequestDto;
import com.ing_hexagonal.application.dto.request.RegisterRequestDto;
import com.ing_hexagonal.application.dto.response.AuthResponseDto;
import com.ing_hexagonal.domain.api.IAuthServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST encargado de exponer los endpoints públicos
 * relacionados con autenticación, como registro e inicio de sesión.
 */
@RestController
@RequestMapping("/autenticacion")
public class AuthRestController {

    private final IAuthServicePort authServicePort;

    public AuthRestController(IAuthServicePort authServicePort) {
        this.authServicePort = authServicePort;
    }

    @PostMapping("/registro")
    public ResponseEntity<String> registrar(@RequestBody RegisterRequestDto request) {
        authServicePort.registrarUsuario(
            request.getNombre(),
            request.getCorreo(),
            request.getContrasena()
        );
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario registrado exitosamente");
    }

    @PostMapping("/iniciar-sesion")
    public ResponseEntity<AuthResponseDto> iniciarSesion(@RequestBody LoginRequestDto request) {
        String token = authServicePort.iniciarSesion(
            request.getCorreo(),
            request.getContrasena()
        );
        return ResponseEntity.ok(new AuthResponseDto(token));
    }
}
