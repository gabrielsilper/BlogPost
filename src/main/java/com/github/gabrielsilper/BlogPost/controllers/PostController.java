package com.github.gabrielsilper.BlogPost.controllers;

import com.github.gabrielsilper.BlogPost.models.entities.Post;
import com.github.gabrielsilper.BlogPost.services.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts(){
        return this.postService.getAll();
    }
}
