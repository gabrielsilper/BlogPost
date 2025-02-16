package com.github.gabrielsilper.BlogPost.services;

import com.github.gabrielsilper.BlogPost.models.dtos.PostCreationDto;
import com.github.gabrielsilper.BlogPost.models.entities.Post;
import com.github.gabrielsilper.BlogPost.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post create(PostCreationDto newPost) {
        return this.postRepository.save(newPost.toEntity());
    }

    public List<Post> getAll(){
        return this.postRepository.findAll();
    }
}
