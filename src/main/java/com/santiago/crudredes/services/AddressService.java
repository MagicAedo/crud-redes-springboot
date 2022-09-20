package com.santiago.crudredes.services;

import com.santiago.crudredes.models.entities.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    void borrarPorId(Long id);

    Address agregar(Address address);

    Optional<Address> verPorId(Long id);

    List<Address> mostrarTodo();

}
