package com.github.gabrielsilper.BlogPost.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException() {
        super("Sorry, this username already exists, please try another one");
    }
}
