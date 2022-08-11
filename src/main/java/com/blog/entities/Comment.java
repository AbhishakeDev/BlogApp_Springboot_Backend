package com.blog.entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    @ManyToOne
    private Post post;
}
