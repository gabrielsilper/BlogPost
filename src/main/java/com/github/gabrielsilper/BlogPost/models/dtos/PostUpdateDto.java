package com.github.gabrielsilper.BlogPost.models.dtos;

import com.github.gabrielsilper.BlogPost.models.entities.Post;

import java.time.LocalDateTime;

public record PostUpdateDto (String title, String content) {
    public Post toEntity(){
        LocalDateTime now = LocalDateTime.now();
        return new Post(null, title, content, null, null, now);
    }
}
