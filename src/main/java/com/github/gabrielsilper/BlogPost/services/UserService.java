package com.github.gabrielsilper.BlogPost.services;

import com.github.gabrielsilper.BlogPost.exceptions.EmailAlreadyExistsException;
import com.github.gabrielsilper.BlogPost.exceptions.UserNotFoundException;
import com.github.gabrielsilper.BlogPost.exceptions.UsernameAlreadyExistsException;
import com.github.gabrielsilper.BlogPost.models.entities.User;
import com.github.gabrielsilper.BlogPost.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User newUser) {
        if (this.userRepository.existsByUsername(newUser.getUsername())) {
            throw new UsernameAlreadyExistsException();
        }

        if (this.userRepository.existsByEmail(newUser.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        String hashedPassword = new BCryptPasswordEncoder().encode(newUser.getPassword());
        newUser.setPassword(hashedPassword);

        return this.userRepository.save(newUser);
    }

    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    public User getById(long id) throws UserNotFoundException {
        return this.userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
