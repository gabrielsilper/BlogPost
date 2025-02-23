package com.github.gabrielsilper.BlogPost.services;

import com.github.gabrielsilper.BlogPost.mocks.UserMock;
import com.github.gabrielsilper.BlogPost.models.dtos.UserCreationDto;
import com.github.gabrielsilper.BlogPost.models.entities.User;
import com.github.gabrielsilper.BlogPost.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

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
        UserCreationDto newUser = UserMock.getSimpleUserCreationDto();
        User userWithId = UserMock.getSimpleUserWithId();

        Mockito.when(userRepository.save(any())).thenReturn(userWithId);

        // When
        User createdUser = userService.create(newUser);

        // Then
        assertEquals(userWithId.getId(), createdUser.getId());
        assertEquals(userWithId.getUsername(), createdUser.getUsername());
        assertEquals(userWithId.getEmail(), createdUser.getEmail());
        assertEquals(userWithId.getPassword(), createdUser.getPassword());

        Mockito.verify(userRepository, Mockito.times(1)).save(any());
    }
}
