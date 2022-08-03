package com.project.springproject2.model;

import com.project.springproject2.domain.Timestamped;
import com.project.springproject2.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ToString
@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Table(name = "post")
@Entity// 테이블과 연계됨을 스프링에게 알려줍니다.
public class Post extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "posts_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

    @Nullable
    @OneToMany (mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @ToString.Exclude // (3)
    private Set<Comment> commentResponseDtoList=new HashSet<>() ;
    public void addComment(Comment comment){
        this.commentResponseDtoList.add(comment);
        comment.setPost(this);
    }

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
