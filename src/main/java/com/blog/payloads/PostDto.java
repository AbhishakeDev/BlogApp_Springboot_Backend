package com.blog.payloads;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
public class PostDto {
    private Integer postId;
    private String title;
    private String content;
    private String imageName;
    private CategoryDto category;
    private UserDto user;
    private Date addedDate;
    private List<CommentDto> comments=new ArrayList<>();
}
