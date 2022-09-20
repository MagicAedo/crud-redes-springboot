package com.santiago.crudredes.controllers;

import com.santiago.crudredes.models.entities.Address;
import com.santiago.crudredes.models.entities.Usuario;
import com.santiago.crudredes.services.AddressService;
import com.santiago.crudredes.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {


    @Autowired
    private UsuarioService service;

    @Autowired
    private AddressService as;


    @GetMapping("/")
    public List<Usuario> mostrarUsuarios(){
        return service.listarUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> mostrarUsuarioPorId(@PathVariable Long id){
        Optional<Usuario> o = service.usuarioById(id);
        if(o.isPresent()){
            return ResponseEntity.ok().body(o.get());}
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarUsuarioPorId(@PathVariable Long id){
        Optional<Usuario> o = service.usuarioById(id);
        if(o.isPresent()){
            service.eliminarUsuario(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<?> agregarUsuario(@RequestBody @Valid Usuario usuario, BindingResult result){

        if(result.hasErrors()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardarUsuario(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarUsuario(@RequestBody @Valid Usuario usuario, BindingResult result,@PathVariable Long id){
        if(result.hasErrors()){
            return ResponseEntity.notFound().build();
        }
        Optional<Usuario> o = service.usuarioById(id);
        if(o.isPresent()) {
            Usuario newUser = o.get();
            Address newAddress = o.get().getDireccion();
            newUser.setNombre(usuario.getNombre());
            newUser.setApellido(usuario.getApellido());
            newUser.setEdad(usuario.getEdad());
            newUser.setEmail(usuario.getEmail());
            newAddress.setNombre_ciudad(usuario.getDireccion().getNombre_ciudad());
            newAddress.setCodigo_postal(usuario.getDireccion().getCodigo_postal());
            newAddress.setDireccion(usuario.getDireccion().getDireccion());
            as.agregar(newAddress);

            return ResponseEntity.ok().body(newUser);
        }
        return ResponseEntity.notFound().build();
    }







}
