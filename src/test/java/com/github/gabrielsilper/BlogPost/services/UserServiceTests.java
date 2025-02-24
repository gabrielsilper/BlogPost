package com.github.gabrielsilper.BlogPost.services;

import com.github.gabrielsilper.BlogPost.exceptions.EmailAlreadyExistsException;
import com.github.gabrielsilper.BlogPost.exceptions.UserNotFoundException;
import com.github.gabrielsilper.BlogPost.exceptions.UsernameAlreadyExistsException;
import com.github.gabrielsilper.BlogPost.mocks.UserMock;
import com.github.gabrielsilper.BlogPost.models.dtos.UserCreationDto;
import com.github.gabrielsilper.BlogPost.models.entities.User;
import com.github.gabrielsilper.BlogPost.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

@DisplayName("UserService tests")
@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserService userService;

    @MockitoBean
    private UserRepository userRepository;

    @Test
    @DisplayName("Should create a user")
    public void createUserTest() {
        // Given
        User newUser = UserMock.getSimpleUser();
        User userWithId = UserMock.getSimpleUserWithId();

        Mockito.when(userRepository.save(any())).thenReturn(userWithId);

        // When
        User createdUser = userService.create(newUser);

        // Then
        assertEquals(userWithId.getId(), createdUser.getId());
        assertEquals(userWithId.getUsername(), createdUser.getUsername());
        assertEquals(userWithId.getEmail(), createdUser.getEmail());
        // TODO - Ver se a senha foi criptografada ou criar um teste para isso, pois como o valor do retorno é mockado, não é possível verificar.
        assertEquals(userWithId.getPassword(), createdUser.getPassword());

        Mockito.verify(userRepository, Mockito.times(1)).save(any());
    }

    @Test
    @DisplayName("Should try to create a user with an existing username and throw UsernameAlreadyExistsException")
    public void createUserWithExistingUsernameTest() {
        // Given
        User newUser = UserMock.getSimpleUser();

        Mockito.when(userRepository.existsByUsername(newUser.getUsername())).thenReturn(true);

        // Then
        assertThrows(UsernameAlreadyExistsException.class, () -> userService.create(newUser));

        Mockito.verify(userRepository, Mockito.times(1)).existsByUsername(newUser.getUsername());
    }

    @Test
    @DisplayName("Should try to create a user with an existing email and throw EmailAlreadyExistsException")
    public void createUserWithExistingEmailTest() {
        // Given
        User newUser = UserMock.getSimpleUser();

        Mockito.when(userRepository.existsByUsername(newUser.getUsername())).thenReturn(false);
        Mockito.when(userRepository.existsByEmail(newUser.getEmail())).thenReturn(true);

        // Then
        assertThrows(EmailAlreadyExistsException.class, () -> userService.create(newUser));

        Mockito.verify(userRepository, Mockito.times(1)).existsByUsername(newUser.getUsername());
        Mockito.verify(userRepository, Mockito.times(1)).existsByEmail(newUser.getEmail());
    }

    @Test
    @DisplayName("Should get all users")
    public void getAllUsersTest() {
        // Given
        User userWithId = UserMock.getSimpleUserWithId();

        Mockito.when(userRepository.findAll()).thenReturn(List.of(userWithId));

        // When
        var users = userService.getAll();

        // Then
        assertEquals(1, users.size());
        assertEquals(userWithId.getId(), users.get(0).getId());
        assertEquals(userWithId.getUsername(), users.get(0).getUsername());
        assertEquals(userWithId.getEmail(), users.get(0).getEmail());
        assertEquals(userWithId.getPassword(), users.get(0).getPassword());

        Mockito.verify(userRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Should get user by id and succeed")
    public void getUserByIdTest() throws UserNotFoundException {
        // Given
        long id = 123L;
        User userWithId = UserMock.getSimpleUserWithId();

        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(userWithId));

        // When
        var user = userService.getById(id);

        // Then
        assertEquals(userWithId.getId(), user.getId());
        assertEquals(userWithId.getUsername(), user.getUsername());
        assertEquals(userWithId.getEmail(), user.getEmail());
        assertEquals(userWithId.getPassword(), user.getPassword());

        Mockito.verify(userRepository, Mockito.times(1)).findById(userWithId.getId());
    }

    @Test
    @DisplayName("Should get user by id and throw UserNotFoundException")
    public void getUserByIdAndThrowUserNotFoundExceptionTest() {
        // Given
        long id = 123L;

        Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());

        // Then
        assertThrows(UserNotFoundException.class, () -> userService.getById(id));

        Mockito.verify(userRepository, Mockito.times(1)).findById(id);
    }
}
