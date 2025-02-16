package com.github.gabrielsilper.BlogPost.models.dtos;

import com.github.gabrielsilper.BlogPost.models.entities.Post;

import java.time.LocalDateTime;

public record PostCreationDto(String title, String text) {
    public Post toEntity(){
        return new Post(null, title, text, null, LocalDateTime.now(), LocalDateTime.now());
    }
}
