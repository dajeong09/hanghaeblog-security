package com.example.hanghaeblogsecurity.repository;

import com.example.hanghaeblogsecurity.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value="select c from Comment c where post_num=:postNum")
    List<Object[]> findAllByPostNum(Long postNum);
    //List<Comment>
}
