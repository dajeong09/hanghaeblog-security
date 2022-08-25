package com.example.hanghaeblogsecurity.service;

import com.example.hanghaeblogsecurity.domain.Comment;
import com.example.hanghaeblogsecurity.domain.Post;
import com.example.hanghaeblogsecurity.dto.CommentDto;
import com.example.hanghaeblogsecurity.dto.PostDto;

import javax.servlet.http.HttpServletRequest;

public interface CommentService {
    Comment createComment(CommentDto commentDto, HttpServletRequest request);
}
