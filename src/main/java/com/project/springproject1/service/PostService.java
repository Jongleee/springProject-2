package com.project.springproject1.service;

import com.project.springproject1.domain.Post;
import com.project.springproject1.domain.PostRepository;
import com.project.springproject1.domain.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long update(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        post.update(requestDto);
        return post.getId();
    }

    public boolean checkPassword(Long id, String password){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        return post.getPassword().equals(password);
    }
}
