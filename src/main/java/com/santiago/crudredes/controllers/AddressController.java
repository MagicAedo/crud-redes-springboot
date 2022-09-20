package com.santiago.crudredes.controllers;


import com.santiago.crudredes.models.entities.Address;
import com.santiago.crudredes.services.AddressService;
import com.santiago.crudredes.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    @Autowired
    private AddressService as;



    @GetMapping("/")
    public List<Address> mostrarAdresses(){
        return as.mostrarTodo();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> mostrarPorId(@PathVariable Long id){
        Optional<Address> o = as.verPorId(id);
        if(o.isPresent()){
            return ResponseEntity.ok().body(as.verPorId(id));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<?> guardarDireccion(@RequestBody @Valid Address address, BindingResult result){
        if(result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(as.agregar(address));

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarDireccion(@RequestBody @Valid Address address, BindingResult result,@PathVariable Long id){
        if(result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Address> o = as.verPorId(id);
        if(o.isPresent()){
            Address newAddres = o.get();
            newAddres.setNombre_ciudad(address.getNombre_ciudad());
            newAddres.setCodigo_postal(address.getCodigo_postal());
            newAddres.setDireccion(address.getDireccion());
            return ResponseEntity.ok().body(as.agregar(newAddres));
        }
        return ResponseEntity.notFound().build();
    }



}
