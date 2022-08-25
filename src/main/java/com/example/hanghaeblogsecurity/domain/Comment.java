package com.example.hanghaeblogsecurity.domain;

import com.example.hanghaeblogsecurity.dto.CommentDto;
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
@ToString(exclude = "commenter")
public class Comment {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name="comment_id")
    private Long commentId;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private Long postNum;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account commenter;

    public Comment(CommentDto commentDto) {
        this.text = commentDto.getText();
        this.postNum = commentDto.getPostNum();
    }


    public void update(CommentDto commentDto) {
        this.text = commentDto.getText();
        this.postNum = commentDto.getPostNum();
    }


}
