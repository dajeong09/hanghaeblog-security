package com.example.hanghaeblogsecurity.domain;

import com.example.hanghaeblogsecurity.dto.PostDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString(exclude = "user")
public class Post extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name="post_id")
    private Long postId;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account user;


    public Post(PostDto postDto) {
        this.writer = postDto.getWriter();
        this.title = postDto.getTitle();
        this.content = postDto.getContent();
    }


    public void update(PostDto postDto) {
        this.writer = postDto.getWriter();
        this.title = postDto.getTitle();
        this.content = postDto.getContent();
    }

}
