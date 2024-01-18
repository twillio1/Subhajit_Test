package com.Test.payload;

import com.Test.entity.Post;
import lombok.Data;

@Data
public class CommentDto {
    private long id;
    private String name;
    private String email;
    private String body;
   // private Post post;
}
