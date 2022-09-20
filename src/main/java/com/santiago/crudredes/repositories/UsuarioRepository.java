package com.santiago.crudredes.repositories;

import com.santiago.crudredes.models.entities.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
}
