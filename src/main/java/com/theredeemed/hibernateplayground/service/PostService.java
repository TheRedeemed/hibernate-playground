package com.theredeemed.hibernateplayground.service;

import com.theredeemed.hibernateplayground.model.entities.Comment;
import com.theredeemed.hibernateplayground.model.entities.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TransactionRequiredException;
import java.util.ArrayList;
import java.util.Arrays;

@Service
@Slf4j
@Transactional
public class PostService {

    @Autowired
    EntityManager entityManager;

    public String savePostWithComments() {
        String response;
        Post post = Post.builder()
                        .title("First Post")
                        .comments(new ArrayList<>())
                        .build();
        post.getComments().addAll(
                Arrays.asList(
                        Comment.builder().review("First comment").build(),
                        Comment.builder().review("Second comment").build(),
                        Comment.builder().review("Third comment").build()
                )
        );
        try {
            entityManager.persist(post);
            response = "POST CREATED!";
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            log.error("Error {}", e.getMessage());
            response = "FAILED TO CREATE POST";
        }
        return response;
    }
}