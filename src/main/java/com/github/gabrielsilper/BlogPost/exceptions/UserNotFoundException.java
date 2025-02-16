package com.github.gabrielsilper.BlogPost.exceptions;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super("User not found!");
    }
}
