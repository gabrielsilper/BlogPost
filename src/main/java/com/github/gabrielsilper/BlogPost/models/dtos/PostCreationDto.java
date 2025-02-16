package com.github.gabrielsilper.BlogPost.models.dtos;

import com.github.gabrielsilper.BlogPost.models.entities.Post;
import com.github.gabrielsilper.BlogPost.models.entities.User;

import java.time.LocalDateTime;

public record PostCreationDto(String title, String text, User user) {
    public Post toEntity(){
        return new Post(null, title, text, user, LocalDateTime.now(), LocalDateTime.now());
    }
}
