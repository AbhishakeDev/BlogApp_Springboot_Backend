package com.blog.controllers;


import com.blog.config.AppConstants;
import com.blog.payloads.ApiResponse;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.services.FileService;
import com.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId) {
        PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
        List<PostDto> postDtos = this.postService.getPostByUser(userId);
        return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) {
        List<PostDto> postDtos = this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUM, required = false) Integer pageNumber, @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                    @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                                    @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {
        PostResponse allPosts = this.postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<PostResponse>(allPosts, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        PostDto postDto = this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePostById(@PathVariable Integer postId) {
        this.postService.deletePost(postId);
        return new ApiResponse("Post Successfully deleted", true);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
        return new ResponseEntity<PostDto>(this.postService.updatePost(postDto, postId), HttpStatus.OK);
    }

    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keyword") String keyword) {
        List<PostDto> postDtos = this.postService.searchPosts(keyword);
        return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
    }

//    Method post to upload images

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@PathVariable("postId") Integer postId, @RequestParam("image") MultipartFile image) throws IOException {
        PostDto postDto = this.postService.getPostById(postId);
        String fileName = this.fileService.uploadImage(path, image);
        postDto.setImageName(fileName);
        PostDto updatedPost = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
    }

//    Method to serve images from databases
    @GetMapping(value = "/post/image/{imageName}",produces= MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName")String imageName, HttpServletResponse response)throws IOException{
        InputStream resource = this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }

}
