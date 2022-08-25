package com.example.hanghaeblogsecurity.dto;

import com.example.hanghaeblogsecurity.domain.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Setter
public class PostDto {
    private Long postId;
    private String writer;
    private String title;
    private String content;
    private String userNickname;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<Object[]> commentList;

}
