package com.blog.payloads;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class PostResponse {
    private List<PostDto> content;
    private Integer pageSize;
    private Integer pageNumber;
    private Long totalElements;
    private Integer totalPages;
    private boolean isLastPage;
}
