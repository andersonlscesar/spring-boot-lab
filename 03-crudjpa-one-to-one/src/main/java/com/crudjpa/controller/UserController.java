package com.crudjpa.controller;

import com.crudjpa.controller.dto.CreateAddressDTO;
import com.crudjpa.controller.dto.CreateUserDTO;
import com.crudjpa.controller.dto.UpdateUserDTO;
import com.crudjpa.entity.Address;
import com.crudjpa.entity.User;
import com.crudjpa.service.AddressService;
import com.crudjpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;
    private final AddressService addressService;

    @Autowired
    public UserController(UserService userService, AddressService addressService) {
        this.userService = userService;
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<List<User>> index() {
        return ResponseEntity.ok( userService.getAllUsers() );
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable("userId") String userId ) {
        userService.deleteUserById(userId);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody CreateUserDTO createUserDTO ) {
        UUID id = userService.insert(createUserDTO);
        return ResponseEntity.created(URI.create("/v1/users" + id)).build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> update(@PathVariable("userId") String userId, @RequestBody UpdateUserDTO updateUserDTO) {
        userService.updateUserById(userId, updateUserDTO);
        return ResponseEntity.noContent().build();
    }

    /**
     * Cria endereço para determinado usuário
     * */

    @PostMapping("/{userId}/address")
    public ResponseEntity<Void> createAddress(@PathVariable("userId") String userId, @RequestBody CreateAddressDTO createAddressDTO) {
        UUID id = addressService.insertAddress(userId, createAddressDTO);
        return ResponseEntity.created(URI.create("/v1/"+ userId + "/address" + id.toString())).build();
    }

    /**
     * Listando endereço de determinado usuário
     * */

    @GetMapping("/{userId}/address")
    public ResponseEntity<Optional<Address>> listAddress(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(userService.listAddress(userId));
    }
}
