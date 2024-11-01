package com.crudjpa.service;

import com.crudjpa.controller.dto.CreateUserDTO;
import com.crudjpa.controller.dto.UpdateUserDTO;
import com.crudjpa.entity.Address;
import com.crudjpa.entity.User;
import com.crudjpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService( UserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> getUserById(String userId ) {
        try {
            UUID id = UUID.fromString( userId );

            if (userRepository.existsById(id)) {
                return userRepository.findById(id);
            } else {
                return Optional.empty();
            }

        } catch ( IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public void deleteUserById( String userId ) {
        try {
            UUID id = UUID.fromString( userId );
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
                userRepository.deleteById(user.get().getId());
            } else {
                System.out.println("Usuário não encontrado: " + userId);
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Usuário inválido: " + userId);
        }
    }



    public UUID insert(CreateUserDTO createUserDTO) {

        validateCreateUserDTO(createUserDTO);

        User newUser = new User(UUID.randomUUID(), createUserDTO.firstName(), createUserDTO.lastName(), createUserDTO.email(), Instant.now(), null);

        return userRepository.save(newUser).getId();
    }

    private void validateCreateUserDTO(CreateUserDTO dto) {

        if ( dto.firstName() == null || dto.firstName().isBlank() ) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }

        if ( dto.lastName() == null || dto.lastName().isBlank() ) {
            throw new IllegalArgumentException("O sobrenome não pode ser vazio.");
        }

        if ( dto.email() == null || dto.email().isBlank() || !dto.email().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Email inválido.");
        }
    }

    public void updateUserById(String userId, UpdateUserDTO updateUserDTO) {
        UUID id = UUID.fromString( userId );
        var userEntity = userRepository.findById(id);

        if (userEntity.isPresent()) {
            var user  = userEntity.get();

            if (updateUserDTO.firstName() != null ) {
                user.setFirstName( updateUserDTO.firstName() );
            }

            if (updateUserDTO.lastName() != null) {
                user.setLastName( updateUserDTO.lastName() );
            }
            userRepository.save(user);
        }
    }
    /*
    * Address
    * */

    public Optional<Address> listAddress(String userId) {
        var user = userRepository.findById(UUID.fromString(userId)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        var address = user.getAddress();
        return Optional.ofNullable(address);
    }


}
