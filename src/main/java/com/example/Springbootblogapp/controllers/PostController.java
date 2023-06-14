package com.example.Springbootblogapp.controllers;


import com.example.Springbootblogapp.models.Post;
import com.example.Springbootblogapp.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id, Model model) {

        Optional<Post> optionalPost = postService.getById(id);

        if(optionalPost.isPresent()) {
            Post thisPost = optionalPost.get();
            model.addAttribute("post", thisPost);
            return "post";
        } else {
            return "404";
        }
    }
}
