package com.github.gabrielsilper.BlogPost.controllers;

import com.github.gabrielsilper.BlogPost.models.dtos.UserCreationDto;
import com.github.gabrielsilper.BlogPost.models.dtos.UserDto;
import com.github.gabrielsilper.BlogPost.models.entities.User;
import com.github.gabrielsilper.BlogPost.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        List<User> users = userService.getAll();

        return users.stream()
                .map(User::toDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserCreationDto newUser) {
        return this.userService.create(newUser).toDto();
    }
}
