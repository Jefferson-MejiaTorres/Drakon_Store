package com.ing_hexagonal.infrastructure.output.jpa.adapter;

import com.ing_hexagonal.domain.model.UsuarioModel;
import com.ing_hexagonal.domain.spi.IUsuarioPersistencePort;
import com.ing_hexagonal.infrastructure.output.jpa.entity.UsuarioEntity;
import com.ing_hexagonal.infrastructure.output.jpa.repository.IUsuarioRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Adaptador que implementa el puerto de persistencia de usuarios.
 * Traduce entre Model del dominio y Entity de JPA.
 */
public class UsuarioJpaAdapter implements IUsuarioPersistencePort {

    private final IUsuarioRepository usuarioRepository;

    public UsuarioJpaAdapter(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public void guardarUsuario(UsuarioModel usuario) {
        UsuarioEntity entity = toEntity(usuario);
        usuarioRepository.save(entity);
    }

    @Override
    public Optional<UsuarioModel> obtenerPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo)
                .map(this::toModel);
    }

    @Override
    public boolean existeCorreo(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }

    /**
     * Convierte Entity a Model.
     */
    private UsuarioModel toModel(UsuarioEntity entity) {
        UsuarioModel model = new UsuarioModel();
        model.setId(entity.getId());
        model.setNombre(entity.getNombre());
        model.setCorreo(entity.getCorreo());
        model.setContrasena(entity.getContrasena());
        model.setRol(entity.getRol());
        model.setActivo(entity.isActivo());
        return model;
    }

    /**
     * Convierte Model a Entity.
     */
    private UsuarioEntity toEntity(UsuarioModel model) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(model.getId());
        entity.setNombre(model.getNombre());
        entity.setCorreo(model.getCorreo());
        entity.setContrasena(model.getContrasena());
        entity.setRol(model.getRol());
        entity.setActivo(model.isActivo());
        return entity;
    }
}
