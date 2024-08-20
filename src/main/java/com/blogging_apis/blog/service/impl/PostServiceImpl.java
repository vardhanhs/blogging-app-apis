package com.blogging_apis.blog.service.impl;

import com.blogging_apis.blog.entities.Category;
import com.blogging_apis.blog.entities.Post;
import com.blogging_apis.blog.entities.User;
import com.blogging_apis.blog.exceptions.ResourceNotFoundException;
import com.blogging_apis.blog.payloads.PostDto;
import com.blogging_apis.blog.payloads.PostResponse;
import com.blogging_apis.blog.repositories.CategoryRepo;
import com.blogging_apis.blog.repositories.PostRepo;
import com.blogging_apis.blog.repositories.UserRepo;
import com.blogging_apis.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User user = this.userRepo.findById(userId).
                orElseThrow(()->new ResourceNotFoundException("User", "user id", userId));

        Category category = this.categoryRepo.findById(categoryId).
                orElseThrow(()->new ResourceNotFoundException("Category", "category id", categoryId));

        Post post=this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post savedPost=this.postRepo.save(post);
        return this.modelMapper.map(savedPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post=this.postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post", "post id", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        Post updatedPost=this.postRepo.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post", "post id", postId));
        this.postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort= Sort.by(sortBy).descending();
        if(sortDir.equalsIgnoreCase("asc")) {
            sort = Sort.by(sortBy).ascending();
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Post> pagePost=this.postRepo.findAll(pageable);
        List<Post> posts=pagePost.getContent();

        PostResponse postResponse=new PostResponse();
        List<PostDto> postDtos=posts.stream().map(p->this.modelMapper
                .map(p, PostDto.class)).collect(Collectors.toList());

        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPost(Integer postId) {
        Post post=this.postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post", "post id", postId));
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getAllPostByCategory(Integer categoryId) { //implement pagination here
        Category cat = this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category", "category id", categoryId));
        List<Post> posts=this.postRepo.findByCategory(cat);
        return posts.stream().map(post->this.modelMapper
                .map(post, PostDto.class)).collect(Collectors.toList());
    }

    // check this method to directly filter using foreign_key ids.
    @Override
    public List<PostDto> getAllPostByUser(Integer userId) { //implement pagination here
        User user=this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User", "user id", userId));
        List<Post> posts=this.postRepo.findByUser(user);
        return posts.stream().map(post->this.modelMapper
                .map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts= this.postRepo.findByTitleContaining(keyword);
        return posts.stream().map(post->this.modelMapper.map(post, PostDto.class)).toList();
    }
}
