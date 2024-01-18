package com.Test.controller;

import com.Test.payload.PostDto;
import com.Test.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto, BindingResult bindingResult)
    {
      if(bindingResult.hasErrors())
      {
          return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
     PostDto dto= postService.createPost(postDto);
      return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> findAllPosts(
            @RequestParam(name = "pageNo", defaultValue = "0",required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "2",required = false) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "title",required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "ASC",required = false) String sortDir
    ){
        List<PostDto> dtos= postService.allPosts(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable long postId)
    {
      postService.deletePost(postId);
      return new ResponseEntity<>("Post is deleted successfully",HttpStatus.OK);
    }
}
