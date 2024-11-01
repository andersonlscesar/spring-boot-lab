package com.crudjpa.service;

import com.crudjpa.controller.dto.CreateAddressDTO;
import com.crudjpa.entity.Address;
import com.crudjpa.repository.AddressRepository;
import com.crudjpa.repository.UserRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    public UUID insertAddress(String userId, CreateAddressDTO createAddressDTO) {

        var user = userRepository.findById(UUID.fromString(userId)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if ( createAddressDTO.number() == null || createAddressDTO.number().isBlank() ) {
            throw new IllegalArgumentException("The number field is required");
        }

        if ( createAddressDTO.street() == null || createAddressDTO.street().isBlank() ) {
            throw new IllegalArgumentException("The street field is required");
        }

        var address = new Address();

        address.setIdAddress(UUID.randomUUID());
        address.setNumber(createAddressDTO.number());
        address.setStreet(createAddressDTO.street());
        address.setUser(user);
        addressRepository.save(address);

        return address.getIdAddress();

    }

}
