package com.example.hanghaeblogsecurity.controller;

import com.example.hanghaeblogsecurity.domain.Comment;
import com.example.hanghaeblogsecurity.domain.Post;
import com.example.hanghaeblogsecurity.dto.CommentDto;
import com.example.hanghaeblogsecurity.dto.PostDto;
import com.example.hanghaeblogsecurity.service.CommentService;
import com.example.hanghaeblogsecurity.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment")
    public Comment createComment(@RequestBody CommentDto commentDto, HttpServletRequest request) {
        return commentService.createComment(commentDto, request);
    }
}
