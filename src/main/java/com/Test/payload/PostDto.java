package com.Test.payload;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@Data
public class PostDto {
    private long id;
    @Size(min = 2, max = 50, message = "title not less than 2")
    private String title;
    @Size(min = 4, max = 50, message = "description not less than 4")
    private String description;
    @Size(min = 4, max = 50, message = "content not less than 2")
    private String content;
    private String message;
}
