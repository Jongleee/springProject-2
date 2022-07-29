package com.project.springproject1.controller;

import com.project.springproject1.domain.Post;
import com.project.springproject1.domain.PostRepository;
import com.project.springproject1.domain.PostRequestDto;
import com.project.springproject1.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;

    @GetMapping("/api/post")
    public List<Post> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    @GetMapping("/api/post/{id}")
    public List<Post> getPosts(@PathVariable Long id) {
        return postRepository.findPostById(id);
    }

    @PostMapping("/api/post")
    public Post createPost(@RequestBody PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        return postRepository.save(post);
    }

    @PostMapping("/api/post/{id}")
    public boolean checkPassword(@PathVariable Long id, @RequestBody PostRequestDto requestDto) throws Exception {
        return postService.checkPassword(id, requestDto.getPassword());
    }

    @PutMapping("/api/post/{id}")
    public List<Post> updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) throws Exception {
        postService.update(id, requestDto);
        return postRepository.findPostById(id);
    }

    @PutMapping("/api/post/password/{id}")
    public boolean updatePost1(@PathVariable Long id, @RequestBody PostRequestDto requestDto) throws Exception {
        if (postService.checkPassword(id, requestDto.getPassword())) {
            postService.update(id, requestDto);
            return true;
        }
        return false;
    }

    @DeleteMapping("/api/post/{id}")
    public boolean deletePost(@PathVariable Long id) throws Exception {
        postRepository.deleteById(id);
        return true;
    }

    @DeleteMapping("/api/post/password/{id}")
    public boolean deletePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) throws Exception {
        if (postService.checkPassword(id, requestDto.getPassword())) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }

}