package com.github.gabrielsilper.BlogPost.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BlogpostController {

    @GetMapping("/live")
    public String live(){
        return "Blogpost API is working...";
    }
}
