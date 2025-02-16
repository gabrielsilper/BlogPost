package com.github.gabrielsilper.BlogPost.repositories;

import com.github.gabrielsilper.BlogPost.models.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
