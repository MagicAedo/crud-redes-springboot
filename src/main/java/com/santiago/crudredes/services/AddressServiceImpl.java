package com.santiago.crudredes.services;

import com.santiago.crudredes.models.entities.Address;
import com.santiago.crudredes.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressRepository ar;



    @Override
    public void borrarPorId(Long id) {
        ar.deleteById(id);
    }

    @Override
    public Address agregar(Address address) {
        return ar.save(address);
    }

    @Override
    public Optional<Address> verPorId(Long id) {
        return ar.findById(id);
    }

    @Override
    public List<Address> mostrarTodo() {
        return (List<Address>) ar.findAll();
    }
}
