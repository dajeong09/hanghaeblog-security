package com.example.hanghaeblogsecurity.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class CommentDto {
    private Long commentId;
    private String text;
    private Long postNum;
    private String commenterNickname;
}
