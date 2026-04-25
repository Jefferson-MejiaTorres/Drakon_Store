package com.ing_hexagonal.domain.usecase;

import com.ing_hexagonal.domain.api.IAuthServicePort;
import com.ing_hexagonal.domain.exception.DomainException;
import com.ing_hexagonal.domain.model.Rol;
import com.ing_hexagonal.domain.model.UsuarioModel;
import com.ing_hexagonal.domain.spi.IPasswordEncoderPort;
import com.ing_hexagonal.domain.spi.IJwtProviderPort;
import com.ing_hexagonal.domain.spi.IUsuarioPersistencePort;

/**
 * Caso de uso de autenticación.
 * Implementa la lógica de negocio: registro e inicio de sesión.
 */
public class AuthUseCase implements IAuthServicePort {

    private final IUsuarioPersistencePort usuarioPersistencePort;
    private final IPasswordEncoderPort passwordEncoderPort;
    private final IJwtProviderPort jwtProviderPort;

    public AuthUseCase(IUsuarioPersistencePort usuarioPersistencePort,
                       IPasswordEncoderPort passwordEncoderPort,
                       IJwtProviderPort jwtProviderPort) {
        this.usuarioPersistencePort = usuarioPersistencePort;
        this.passwordEncoderPort = passwordEncoderPort;
        this.jwtProviderPort = jwtProviderPort;
    }

    @Override
    public void registrarUsuario(String nombre, String correo, String contrasena) {
        // Validar datos
        if (nombre == null || nombre.isBlank()) {
            throw new DomainException("El nombre es obligatorio");
        }

        if (correo == null || correo.isBlank()) {
            throw new DomainException("El correo es obligatorio");
        }

        if (contrasena == null || contrasena.isBlank()) {
            throw new DomainException("La contraseña es obligatoria");
        }

        // Verificar que no exista otro usuario con ese correo
        if (usuarioPersistencePort.existeCorreo(correo)) {
            throw new DomainException("Ya existe un usuario con ese correo");
        }

        // Encriptar contraseña
        String contrasenaEncriptada = passwordEncoderPort.encriptar(contrasena);

        // Crear y guardar usuario
        UsuarioModel usuarioNuevo = new UsuarioModel();
        usuarioNuevo.setNombre(nombre);
        usuarioNuevo.setCorreo(correo);
        usuarioNuevo.setContrasena(contrasenaEncriptada);
        usuarioNuevo.setRol(Rol.USUARIO); // Rol por defecto
        usuarioNuevo.setActivo(true);

        usuarioPersistencePort.guardarUsuario(usuarioNuevo);
    }

    @Override
    public String iniciarSesion(String correo, String contrasena) {
        // Validar datos
        if (correo == null || correo.isBlank()) {
            throw new DomainException("El correo es obligatorio");
        }

        if (contrasena == null || contrasena.isBlank()) {
            throw new DomainException("La contraseña es obligatoria");
        }

        // Buscar usuario por correo
        UsuarioModel usuario = usuarioPersistencePort.obtenerPorCorreo(correo)
                .orElseThrow(() -> new DomainException("Credenciales inválidas"));

        // Verificar que el usuario esté activo
        if (!usuario.isActivo()) {
            throw new DomainException("El usuario está inactivo");
        }

        // Verificar contraseña
        if (!passwordEncoderPort.coincidir(contrasena, usuario.getContrasena())) {
            throw new DomainException("Credenciales inválidas");
        }

        // Generar y retornar token JWT
        return jwtProviderPort.generarToken(correo);
    }
}
