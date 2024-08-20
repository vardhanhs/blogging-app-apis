package com.blogging_apis.blog.service;

import com.blogging_apis.blog.entities.Post;
import com.blogging_apis.blog.payloads.PostDto;
import com.blogging_apis.blog.payloads.PostResponse;

import java.util.List;

public interface PostService {

    //create method
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    //update
    PostDto updatePost(PostDto postDto, Integer postId);

    //delete
    void deletePost(Integer postId);

    //get all
    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //get single post
    PostDto getPost(Integer postId);

    // get all posts from a category
    List<PostDto> getAllPostByCategory(Integer categoryId);

    //get all post from a user
    List<PostDto> getAllPostByUser(Integer userId);

    //search posts
    List<PostDto> searchPosts(String keyword);

}
