package com.example.hanghaeblogsecurity.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
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

}
