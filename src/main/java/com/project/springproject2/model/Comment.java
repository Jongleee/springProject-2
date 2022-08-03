package com.project.springproject2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.springproject2.domain.Timestamped;
import com.project.springproject2.dto.CommentRequestDto;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
@Entity
        @Table(name = "commentResponseDtoList")
@NoArgsConstructor
public class Comment extends Timestamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Long postId;
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "posts_id")
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
