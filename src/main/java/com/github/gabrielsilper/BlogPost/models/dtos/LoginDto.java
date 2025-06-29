package com.github.gabrielsilper.BlogPost.models.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @NotBlank String username,
        @NotBlank String password) {
}
