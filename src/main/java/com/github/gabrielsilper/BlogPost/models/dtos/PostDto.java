package com.github.gabrielsilper.BlogPost.models.dtos;

import java.time.LocalDateTime;

public record PostDto(long id, String title, String content, Long user, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
