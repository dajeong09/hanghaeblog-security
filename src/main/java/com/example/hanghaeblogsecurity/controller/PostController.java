package com.example.hanghaeblogsecurity.controller;

import com.example.hanghaeblogsecurity.domain.Post;
import com.example.hanghaeblogsecurity.dto.PostDto;
import com.example.hanghaeblogsecurity.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public Post createPost(@RequestBody PostDto postDto, HttpServletRequest request) {
        return postService.createPost(postDto, request);
    }

    @PutMapping("/post/{postId}")
    public PostDto updatePost(@PathVariable Long postId, @RequestBody PostDto postDto, HttpServletRequest request) {
        return postService.updatePost(postId, postDto, request);
    }

    @DeleteMapping("/post/{postId}")
    public void deletePost(@PathVariable Long postId, HttpServletRequest request) {
        postService.deletePost(postId, request);
    }

    @GetMapping("/post/{postId}")
    public PostDto getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }
}
