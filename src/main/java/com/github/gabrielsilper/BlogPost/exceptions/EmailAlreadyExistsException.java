package com.github.gabrielsilper.BlogPost.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException() {
        super("Email already associated with an user");
    }
}
