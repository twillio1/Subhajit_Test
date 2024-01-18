package com.Test.service.impl;

import com.Test.entity.Post;
import com.Test.payload.PostDto;
import com.Test.repository.PostRepository;
import com.Test.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    public PostDto mapToDto(Post post)
    {
        PostDto dto=new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setDescription(post.getDescription());

        return dto;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post=new Post();

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());

        Post saved = postRepository.save(post);

        PostDto dto = mapToDto(saved);
        return dto;
    }

    @Override
    public List<PostDto> allPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> all = postRepository.findAll(pageable);
        List<PostDto> collect = all.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        return collect;
    }
}
