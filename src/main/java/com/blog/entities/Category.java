package com.blog.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@Data
public class Category {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer categoryId;

        @Column(name = "Title",length = 100,nullable = false)
        private String categoryTitle;
        @Column(name = "Description")
        private String categoryDescription;

        @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
        private List<Post> posts=new ArrayList<>();
}
