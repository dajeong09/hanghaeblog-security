package com.example.hanghaeblogsecurity.service;

import com.example.hanghaeblogsecurity.domain.Post;
import com.example.hanghaeblogsecurity.dto.PostDto;

import javax.servlet.http.HttpServletRequest;


public interface PostService {
    Post createPost(PostDto postDto, HttpServletRequest request);
    PostDto updatePost(Long postId, PostDto postDto, HttpServletRequest request);
    void deletePost(Long postId, HttpServletRequest request);
    PostDto getPost(Long postId);

}
