package com.blog.repositories;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

//    @Query("select p from Post p where p.title like :key")
//    List<Post> findByTitleContaining(@Param("key") String keyword);
    List<Post> findByTitleContaining(String keyword);
}
