package com.project.springproject2.model;

import com.project.springproject2.domain.Timestamped;
import com.project.springproject2.dto.CommentResponseDtoList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private Long postId;
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

    @ManyToOne
    @JoinColumn(name="com.project.springproject2.model.post.post_id")
    @ToString.Exclude // (3)
    private Post post;

    public Comment(Long postId, String content) {
        this.postId = postId;
        this.content = content;
    }

    public Comment(CommentResponseDtoList requestDto, String writer){
        this.content = requestDto.getContent();
        this.author = author;
    }

    public void update(CommentResponseDtoList requestDto) {
        this.content = requestDto.getContent();
    }
}
