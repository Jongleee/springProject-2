package com.project.springproject2.model;

import com.project.springproject2.domain.Timestamped;
import com.project.springproject2.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString
@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity(name = "posts") // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Post extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "com.project.springproject2.model.post.post_id")
    private Long id;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "post") // (4)
    @ToString.Exclude // (3)
    private List<Comment> comments = new ArrayList<>();


    public Post(String author, String content, String title,String password) {
        this.author = author;
        this.content = content;
        this.title=title;
    }

    public Post(PostRequestDto requestDto) {
        this.author = requestDto.getAuthor();
        this.content = requestDto.getContent();
        this.title=requestDto.getTitle();
    }

    public Post(String nickname,PostRequestDto requestDto) {
        this.author = nickname;
        this.content = requestDto.getContent();
        this.title=requestDto.getTitle();
    }

    public void update(PostRequestDto requestDto) {
        this.author = requestDto.getAuthor();
        this.content = requestDto.getContent();
        this.title=requestDto.getTitle();
    }
}
