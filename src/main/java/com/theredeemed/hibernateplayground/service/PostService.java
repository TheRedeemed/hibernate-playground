package com.theredeemed.hibernateplayground.service;

import com.theredeemed.hibernateplayground.model.entities.Comment;
import com.theredeemed.hibernateplayground.model.entities.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
//        post.getComments().addAll(
//                Arrays.asList(
//                        Comment.builder().review("First comment").build(),
//                        Comment.builder().review("Second comment").build(),
//                        Comment.builder().review("Third comment").build()
//                )
//        );

        //In a bi-directional relational we will have to use the utility method to synchronize both post and comments
        post.addComment(Comment.builder().review("First comment").build());
        post.addComment(Comment.builder().review("Second comment").build());
        post.addComment(Comment.builder().review("Third comment").build());
        try {
            entityManager.persist(post);
            response = "POST CREATED!";
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            log.error("Error {}", e.getMessage());
            response = "FAILED TO CREATE POST";
        }
        return response;
    }

    public List<Post> getAllposts() {
//        Post post = entityManager.find(Post.class, 1L);
//        return Arrays.asList(post);
//        Query getPostQ = entityManager.createQuery("SELECT p FROM Post p WHERE Title = ?1");
//        getPostQ.setParameter(1, "First Post");
        return entityManager.createQuery("SELECT p FROM Post p").getResultList();
    }
}