package com.github.gabrielsilper.BlogPost.models.dtos;

import com.github.gabrielsilper.BlogPost.models.entities.User;

public record UserCreationDto(String username, String email, String password) {

    public User toEntity(){
        return new User(null, username, email, password);
    }
}
