package com.theredeemed.hibernateplayground.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "POST")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @OneToMany(
            mappedBy = "post",  //The mappedBy property is used in bi-directional one-to-many to specify the mapped field on the related entity (Comment - in this case)
            cascade = CascadeType.ALL,  //Once a row is deleted, the cascade ALL indicates that all related entities should also be deleted
            orphanRemoval = true
    )
//    @JoinColumn(name = "post_id")   //The @JoinColumn annotation prevents the creation of a third mapping table. It adds a post_id column to the comment table to keep tack of the foreign key
    @JsonIgnoreProperties("post")   //This annotation will prevent circular referencing between post and comment
    List<Comment> comments; //= new ArrayList<>();

    //Method used to synchronize both sides of the bi-directional relationship
    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setPost(this);
    }

    //Method used to synchronize both sides of the bi-directional relationship
    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setPost(null);
    }
}
