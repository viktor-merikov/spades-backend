package com.backend.spades.controller;

import com.backend.spades.dto.UserDto;
import com.backend.spades.exception.NotFoundException;
import com.backend.spades.model.User;
import com.backend.spades.repository.UserRepository;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<UserDto> getAll() {
        return userRepository.findAll().stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getPhone(),
                        user.getAddress()
                )).toList();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("User with id: " + id + "not found");
        }
        return user.get();
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody User user) {
        User userSaved = userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userSaved.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public User update(@Valid @RequestBody User user) {
        if (user.getId() == null) {
            throw new NotFoundException("User ID is null");
        }
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User with id: " + user.getId() + "not found");
        }
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        userRepository.deleteById(id);
    }
}
