package com.github.gabrielsilper.BlogPost.services;

import com.github.gabrielsilper.BlogPost.exceptions.PostNotFoundException;
import com.github.gabrielsilper.BlogPost.exceptions.UserNotFoundException;
import com.github.gabrielsilper.BlogPost.models.dtos.PostCreationDto;
import com.github.gabrielsilper.BlogPost.models.entities.Post;
import com.github.gabrielsilper.BlogPost.models.entities.User;
import com.github.gabrielsilper.BlogPost.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    @Autowired
    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public Post create(Long userId, PostCreationDto newPost) throws UserNotFoundException {
        User user = userService.getById(userId);
        Post post = newPost.toEntity();
        post.setUser(user);
        return this.postRepository.save(post);
    }

    public List<Post> getAll(){
        return this.postRepository.findAll();
    }

    public Post getById(Long id) throws PostNotFoundException {
        return this.postRepository.findById(id).orElseThrow(PostNotFoundException::new);
    }
}
