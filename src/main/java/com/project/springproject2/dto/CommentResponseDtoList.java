package com.project.springproject2.dto;

import com.project.springproject2.model.Comment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class CommentResponseDtoList {
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final String author;
    private final Long Id;
    private final List<?> commentResponseDtoList=new ArrayList<>();

    public CommentResponseDtoList(Comment comment) {

        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.author = comment.getAuthor();
        this.Id = comment.getId();
    }
}