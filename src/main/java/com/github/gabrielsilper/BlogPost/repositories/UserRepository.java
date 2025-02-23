package com.github.gabrielsilper.BlogPost.repositories;

import com.github.gabrielsilper.BlogPost.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
