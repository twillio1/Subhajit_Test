package com.Test.controller;

import com.Test.payload.CommentDto;
import com.Test.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/{postId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable long postId)
    {
       CommentDto dto= commentService.createComment(commentDto,postId);
       return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDto>> findCommentsById(@PathVariable long postId)
    {
        List<CommentDto> dtos = commentService.findCommentsById(postId);
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto,@RequestParam long commentId,@RequestParam long postId)
    {
       CommentDto dto= commentService.updatePost(commentDto,commentId,postId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
