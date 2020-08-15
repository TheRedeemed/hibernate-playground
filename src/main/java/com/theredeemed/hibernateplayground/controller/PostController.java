package com.theredeemed.hibernateplayground.controller;

import com.theredeemed.hibernateplayground.model.entities.Post;
import com.theredeemed.hibernateplayground.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createPost() {
        String response = postService.savePostWithComments();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "view-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Post>> getPosts() {
        return new ResponseEntity<>(postService.getAllposts(), HttpStatus.FOUND);
    }
}
