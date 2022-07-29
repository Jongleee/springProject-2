package com.project.springproject1.domain;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private String author;
    private String contents;
    private String title;
    private String password;
}
