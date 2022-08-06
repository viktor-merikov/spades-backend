package com.backend.spades.controller;

import com.backend.spades.dto.UserDto;
import com.backend.spades.exception.NotFoundException;
import com.backend.spades.mapper.UserMapper;
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

    private final UserMapper userMapper = new UserMapper();

    @GetMapping
    public List<UserDto> getAll() {
        return userMapper.listToDto(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public UserDto get(@PathVariable long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("User with id: " + id + "not found");
        }
        return userMapper.toDto(user.get());
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody UserDto userDto) {
        User userSaved = userRepository.save(userMapper.toEntity(userDto));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userSaved.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public User update(@Valid @RequestBody UserDto userDto) {
        if (userDto.getId() == null) {
            throw new NotFoundException("User ID is null");
        }
        Optional<User> optionalUser = userRepository.findById(userDto.getId());
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User with id: " + userDto.getId() + "not found");
        }
        return userRepository.save(userMapper.toEntity(userDto));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        userRepository.deleteById(id);
    }
}
