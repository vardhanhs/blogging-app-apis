package com.blogging_apis.blog.service.impl;

import com.blogging_apis.blog.entities.Comment;
import com.blogging_apis.blog.entities.Post;
import com.blogging_apis.blog.exceptions.ResourceNotFoundException;
import com.blogging_apis.blog.payloads.CommentDto;
import com.blogging_apis.blog.repositories.CommentRepo;
import com.blogging_apis.blog.repositories.PostRepo;
import com.blogging_apis.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post", "postId", postId));

        Comment comment=this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment=this.commentRepo.save(comment);

        return this.modelMapper.map(savedComment, CommentDto.class);

    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment =this.commentRepo.findById(commentId).
                orElseThrow(()->new ResourceNotFoundException("Comment", "commentId", commentId));
        this.commentRepo.delete(comment);
    }
}
