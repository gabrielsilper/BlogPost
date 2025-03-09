package com.github.gabrielsilper.BlogPost.controllers;

import com.github.gabrielsilper.BlogPost.exceptions.PostNotFoundException;
import com.github.gabrielsilper.BlogPost.models.dtos.PostCreationDto;
import com.github.gabrielsilper.BlogPost.models.dtos.PostDto;
import com.github.gabrielsilper.BlogPost.models.dtos.PostUpdateDto;
import com.github.gabrielsilper.BlogPost.models.entities.Post;
import com.github.gabrielsilper.BlogPost.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostDto> getAllPosts(){
        return this.postService
                .getAll()
                .stream()
                .map(Post::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public PostDto getPostById(@PathVariable Long id) throws PostNotFoundException {
        return this.postService.getById(id).toDto();
    }

    @PutMapping("/{id}")
    public PostDto updatePost(@PathVariable Long id, @RequestBody PostUpdateDto updatedPost) throws PostNotFoundException {
        return this.postService.update(id, updatedPost.toEntity()).toDto();
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) throws PostNotFoundException {
        this.postService.delete(id);
    }
}
