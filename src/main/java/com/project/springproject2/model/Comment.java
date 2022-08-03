package com.project.springproject2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.springproject2.domain.Timestamped;
import com.project.springproject2.dto.CommentRequestDto;
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "com.project.springproject2.model.post.post_id")
    @ToString.Exclude // (3)
    private Post post;

    public Comment(Long postId, String content) {
        this.postId = postId;
        this.content = content;
    }

    public Comment(CommentRequestDto requestDto) {
        this.postId = requestDto.getPostId();
        this.content = requestDto.getContent();
    }

    public Comment(CommentRequestDto requestDto, String author) {
        this.postId = requestDto.getPostId();
        this.content = requestDto.getContent();
        this.author = author;
    }

    public void update(CommentRequestDto requestDto) {

        this.content = requestDto.getContent();
    }
}
