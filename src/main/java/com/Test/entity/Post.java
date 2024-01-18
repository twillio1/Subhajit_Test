package com.Test.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "posts")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private String content;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "post",orphanRemoval = true)
    private List<Comment> comments;
}
