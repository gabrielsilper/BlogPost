package com.github.gabrielsilper.BlogPost.mocks;

import com.github.gabrielsilper.BlogPost.models.dtos.UserCreationDto;
import com.github.gabrielsilper.BlogPost.models.entities.User;

public class UserMock {

    public static User getSimpleUser() {
        return new User(null, "TesteFulano", "testefulano@email.com", "great123");
    }

    public static UserCreationDto getSimpleUserCreationDto() {
        return new UserCreationDto("TesteFulano", "testefulano@email.com", "great123");
    }

    public static User getSimpleUserWithId() {
        return new User(123L, "TesteFulano", "testefulano@email.com", "great123");
    }
}
