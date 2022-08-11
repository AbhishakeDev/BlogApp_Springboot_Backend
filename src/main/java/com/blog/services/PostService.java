package com.blog.services;

import com.blog.entities.Post;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
    PostDto updatePost(PostDto postDto,Integer postId);
    void deletePost(Integer postId);

    PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);
    PostDto getPostById(Integer postId);
    List<PostDto> getPostByCategory(Integer categoryId);
    List<PostDto> getPostByUser(Integer userId);
    List<PostDto> searchPosts(String keyword);
}
