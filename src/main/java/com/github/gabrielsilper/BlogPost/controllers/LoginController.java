package com.github.gabrielsilper.BlogPost.controllers;

import com.github.gabrielsilper.BlogPost.models.dtos.LoginDto;
import com.github.gabrielsilper.BlogPost.models.dtos.TokenDto;
import com.github.gabrielsilper.BlogPost.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping()
    public TokenDto login(@RequestBody @Valid LoginDto loginDto) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                loginDto.username(),
                loginDto.password()
        );

        Authentication auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken(auth.getName());

        return new TokenDto(token);
    }
}
