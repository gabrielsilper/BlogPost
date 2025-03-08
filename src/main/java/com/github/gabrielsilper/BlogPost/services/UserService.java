package com.github.gabrielsilper.BlogPost.services;

import com.github.gabrielsilper.BlogPost.exceptions.EmailAlreadyExistsException;
import com.github.gabrielsilper.BlogPost.exceptions.UserNotFoundException;
import com.github.gabrielsilper.BlogPost.exceptions.UsernameAlreadyExistsException;
import com.github.gabrielsilper.BlogPost.models.entities.User;
import com.github.gabrielsilper.BlogPost.repositories.UserRepository;
import com.github.gabrielsilper.BlogPost.utils.EntityUpdater;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    public User update(Long id, User updatedUser) throws UserNotFoundException {
        User oldUser = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        if (Objects.nonNull(updatedUser.getUsername()) && this.userRepository.existsByUsername(updatedUser.getUsername())) {
            throw new UsernameAlreadyExistsException();
        }

        if (Objects.nonNull(updatedUser.getEmail()) && this.userRepository.existsByEmail(updatedUser.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        if (Objects.nonNull(updatedUser.getPassword())){
            String hashedPassword = new BCryptPasswordEncoder().encode(updatedUser.getPassword());
            updatedUser.setPassword(hashedPassword);
        }

        EntityUpdater.mergeNotNullProperties(updatedUser, oldUser);

        return userRepository.save(oldUser);
    }

    public void delete(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }
}
