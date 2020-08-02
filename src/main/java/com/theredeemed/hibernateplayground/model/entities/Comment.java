package com.theredeemed.hibernateplayground.model.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "COMMENT")
@Data
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false, unique = true)
    Long id;

    @Column(name = "REVIEW", nullable = false, updatable = true, length = 4000)
    String review;
}