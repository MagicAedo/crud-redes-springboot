package com.santiago.crudredes.services;

import com.santiago.crudredes.models.entities.Address;
import com.santiago.crudredes.models.entities.Usuario;
import com.santiago.crudredes.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;



@Service
public class UsuarioServiceImpl implements UsuarioService{
    @Autowired
    private UsuarioRepository repository;

    @Override
    public List<Usuario> listarUsuarios() {
        return (List<Usuario>) repository.findAll();
    }

    @Override
    public Optional<Usuario> usuarioById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Usuario guardarUsuario(Usuario usuario) {

        return repository.save(usuario);
    }

    @Override
    public void eliminarUsuario(Long id) {
        repository.deleteById(id);
    }
}
