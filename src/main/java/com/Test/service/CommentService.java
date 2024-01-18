package com.Test.service;

import com.Test.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, long postId);

    List<CommentDto> findCommentsById(long postId);

    CommentDto updatePost(CommentDto commentDto, long commentId, long postId);
}
