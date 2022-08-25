package com.example.hanghaeblogsecurity.service;

import com.example.hanghaeblogsecurity.domain.Account;
import com.example.hanghaeblogsecurity.domain.Comment;
import com.example.hanghaeblogsecurity.domain.Post;
import com.example.hanghaeblogsecurity.dto.CommentDto;
import com.example.hanghaeblogsecurity.repository.CommentRepository;
import com.example.hanghaeblogsecurity.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final AuthService authService;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    @Override
    public Comment createComment(CommentDto commentDto, HttpServletRequest request) {
//        if (!postRepository.findById(commentDto.getPostNum()).isPresent()) {
//            throw new Error("글이 존재하지 않습니다.");
//        }
        Comment comment = new Comment(commentDto);
        Account account = authService.getMyUserWithAuthorities();
        comment.setCommenter(account);
        commentRepository.save(comment);
        return comment;
    }

}
