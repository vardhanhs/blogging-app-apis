package com.blogging_apis.blog.controllers;

import com.blogging_apis.blog.entities.Post;
import com.blogging_apis.blog.payloads.ApiResponse;
import com.blogging_apis.blog.payloads.PostDto;
import com.blogging_apis.blog.payloads.PostResponse;
import com.blogging_apis.blog.service.PostService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;
    //create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@PathVariable int userId, @PathVariable int categoryId, @RequestBody PostDto postDto) {
        PostDto createdPostDto = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(createdPostDto, HttpStatus.CREATED);
    }

    // get by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable int userId) {
        List<PostDto> posts=this.postService.getAllPostByUser(userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // get by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable int categoryId) {
        List<PostDto> posts=this.postService.getAllPostByCategory(categoryId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    //get all posts
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam( value = "pageNumber", defaultValue = "0",required = false) Integer pageNumber,
            @RequestParam(value ="pageSize", defaultValue = "10", required = false ) Integer pageSize,
            @RequestParam(value ="sortBy", defaultValue = "postId", required = false) String sortBy,
            @RequestParam(value ="sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        PostResponse posts = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // get post by id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable int postId) {
        PostDto postDto = this.postService.getPost(postId);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    //update post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable int postId, @RequestBody PostDto postDto) {
        PostDto updatedPost=this.postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    //delete post
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable int postId) {
        this.postService.deletePost(postId);
        ApiResponse apiResponse= new ApiResponse("Post is deleted successfully", true);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    //search
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostsByTitle(@PathVariable String keywords) {
        List<PostDto> result=this.postService.searchPosts(keywords);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
