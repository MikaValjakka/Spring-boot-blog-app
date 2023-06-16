package com.example.Springbootblogapp.services;

import com.example.Springbootblogapp.models.Post;
import com.example.Springbootblogapp.repositories.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;

    // Find one post by its id
    public Optional<Post> getById(long id) {
        return postRepo.findById(id);
    }

    //List all posts
    public List<Post> getAll() {


       return postRepo.findAll();
    }

    public Post save(Post savedPost){
        if(savedPost.getId() == null) {
            savedPost.setCreatedAt(LocalDateTime.now());
        }
        savedPost.setUpdatedAt(LocalDateTime.now());
        return postRepo.save(savedPost);
    }

    public void delete(Post deletablePost) {
        postRepo.delete(deletablePost);
    }


}
