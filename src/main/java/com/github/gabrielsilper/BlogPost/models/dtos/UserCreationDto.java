package com.github.gabrielsilper.BlogPost.models.dtos;

import com.github.gabrielsilper.BlogPost.models.entities.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserCreationDto(
        @NotBlank(message = "is required") @Size(min = 3, max = 25, message = "must contain between 3 and 25 characters") @Pattern(regexp = "\\S+", message = "cannot contain whitespace") String username,

        @NotBlank(message = "is required") @Email(message = "must be a valid email") String email,

        @NotBlank(message = "is required") @Size(min = 8, max = 25, message = "must contain between 8 and 25 characters") String password) {

    public User toEntity() {
        return new User(null, username, email, password);
    }
}
