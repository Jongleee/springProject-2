package com.project.springproject2.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private String author;
    private String contents;
    private String title;
    private String password;
}