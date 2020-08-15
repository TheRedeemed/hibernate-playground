package com.theredeemed.hibernateplayground.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "COMMENT")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false, unique = true)
    Long id;

    @Column(name = "REVIEW", nullable = false, updatable=true, length = 4000)
    String review;

    /**
     * This annotation displays mapping type between comment and post - many comment to one post
     * By default many-to-one relationship is eager fetch which means it will fetch the post while fetching
     * the comment as well. Making it fetch LAZY is meant to help with performance
     *
     * Also the comment entity is called the owning side of the relationship because it holds/owns the foreign key to the
     * post record it related to.
     */
    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonManagedReference       //This annotation will prevent circular referencing between post and comment
//    @JsonIgnoreProperties("comment")
    Post post;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        return id != null && id.equals(((Comment) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}