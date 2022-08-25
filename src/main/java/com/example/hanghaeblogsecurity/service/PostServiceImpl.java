package com.example.hanghaeblogsecurity.service;

import com.example.hanghaeblogsecurity.domain.Account;
import com.example.hanghaeblogsecurity.domain.Post;
import com.example.hanghaeblogsecurity.dto.PostDto;
import com.example.hanghaeblogsecurity.jwt.JwtUtils;
import com.example.hanghaeblogsecurity.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final JwtUtils jwtUtils;
    private final PostRepository postRepository;
    private final AuthService authService;

    //사용자 인증
    @Transactional
    public Account validateAccount(HttpServletRequest request) {
        if (!jwtUtils.isValidateToken(request.getHeader("Access-Token"))) {
            return null;
        }
        return jwtUtils.getAccountFromAuthentication();
    }

    @Override
    @Transactional
    public Post createPost(PostDto postDto, HttpServletRequest request) {

        Post post = new Post(postDto);
        Account account = authService.getMyUserWithAuthorities();
        post.setUser(account);
        postRepository.save(post);
        return post;
    }

    @Override
    @Transactional
    public PostDto updatePost(Long postId, PostDto postDto, HttpServletRequest request) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("글이 존재하지 않습니다.")
        );

        if (authService.getMyUserWithAuthorities() == post.getUser()) {
            post.update(postDto);
        } else {
            throw new Error("권한이 없습니다.");
        }
        postDto.setPostId(post.getPostId());
        postDto.setCreatedAt(post.getCreatedAt());
        postDto.setModifiedAt(post.getModifiedAt());
        postDto.setUserNickname(post.getUser().getNickname());

        return postDto;
    }

    @Override
    public void deletePost(Long postId, HttpServletRequest request) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("글이 존재하지 않습니다.")
        );
        if (authService.getMyUserWithAuthorities() == post.getUser()) {
            postRepository.deleteById(postId);
            System.out.println(postId+"가 삭제되었습니다.");
        } else {
            throw new Error("권한이 없습니다.");
        }
    }
}
