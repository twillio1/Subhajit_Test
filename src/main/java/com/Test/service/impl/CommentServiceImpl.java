package com.Test.service.impl;

import com.Test.entity.Comment;
import com.Test.entity.Post;
import com.Test.exception.ResourceNotFoundException;
import com.Test.payload.CommentDto;
import com.Test.repository.CommentRepository;
import com.Test.repository.PostRepository;
import com.Test.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    public CommentDto mapToDto(Comment comment)
    {
        CommentDto dto=new CommentDto();
        dto.setId(comment.getId());
        dto.setBody(comment.getBody());
        dto.setName(comment.getName());
        dto.setEmail(comment.getEmail());

        return dto;
    }
    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post not found with these id " + postId)
        );

        Comment comment=new Comment();

        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());
        comment.setPost(post);

        Comment saved = commentRepository.save(comment);

        CommentDto dto = mapToDto(saved);
        return dto;
    }

    @Override
    public List<CommentDto> findCommentsById(long postId) {

        List<Comment> all = commentRepository.findByPostId(postId);

        List<CommentDto> collect = all.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public CommentDto updatePost(CommentDto commentDto, long commentId, long postId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment not found with these id: " + commentId)
        );

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post not found with these id: " + postId)
        );

        comment.setName(commentDto.getName());
        comment.setEmail(comment.getEmail());
        comment.setBody(comment.getBody());
        comment.setPost(post);

        Comment saved = commentRepository.save(comment);

        CommentDto dto = mapToDto(saved);

        return dto;
    }
}
