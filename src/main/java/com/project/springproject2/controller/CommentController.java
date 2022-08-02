package com.project.springproject2.controller;

import com.project.springproject2.dto.PostRequestDto;
import com.project.springproject2.model.Message;
import com.project.springproject2.model.Post;
import com.project.springproject2.repository.PostRepository;
import com.project.springproject2.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final PostRepository postRepository;
    private final PostService postService;
///////////////여기부터
    @PostMapping("/api/auth/comment")
    public Post createPost(@RequestBody PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        return postRepository.save(post);
    }

    @GetMapping("/api/comment/{id}")
    public ResponseEntity<?> getPosts(@PathVariable Long id) {
        Message message = new Message();
        List <?> post=postRepository.findPostById(id);
        message.setData(post);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }



    @PutMapping("/api/auth/comment/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) throws Exception {
        postService.update(id, requestDto);
        Message message = new Message();
        List <?> post=postRepository.findPostById(id);
        message.setData(post);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }


    @DeleteMapping("/api/auth/comment/{id}")
    public boolean deletePost(@PathVariable Long id) throws Exception {
        postRepository.deleteById(id);
        return true;
    }


}