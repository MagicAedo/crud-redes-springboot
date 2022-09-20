package com.santiago.crudredes.services;

import com.santiago.crudredes.models.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> listarUsuarios();

    Optional<Usuario> usuarioById(Long id);

    Usuario guardarUsuario(Usuario usuario);

    void eliminarUsuario(Long id);







}
