package com.theredeemed.hibernateplayground.model.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "POST")
@Data
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //IDENTITY strategy relies on an auto-incremented database column to generate the primary key
    @Column(name = "ID", nullable = false, updatable = false, unique = true)
    Long id;

    @Column(name = "TITLE", nullable = false, updatable = true, length = 50)
    String title;

    /**
     * The @OneToMany annotation creates a uni-directional relationship between Post and comment.
     * With a uni-directional relationship, Hibernate will create a mapping table to maintain the mapping between Post and comments tables
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //The @JoinColumn annotation prevents the creation of a third mapping table. It adds a post_id column to the comment table to keep tack of the foreign key
    @JoinColumn(name = "post_id")
    List<Comment> comments = new ArrayList<>();
}
