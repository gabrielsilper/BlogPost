package com.github.gabrielsilper.BlogPost.exceptions;

public class PostNotFoundException extends Exception {
    public PostNotFoundException() {
        super("Post not found!");
    }
}
