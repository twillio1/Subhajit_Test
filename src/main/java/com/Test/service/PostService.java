package com.Test.service;

import com.Test.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> allPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    void deletePost(long postId);
}
