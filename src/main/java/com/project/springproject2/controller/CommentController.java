package com.project.springproject2.controller;

import com.project.springproject2.dto.CommentRequestDto;
import com.project.springproject2.dto.PostRequestDto;
import com.project.springproject2.model.Comment;
import com.project.springproject2.model.Message;
import com.project.springproject2.model.Post;
import com.project.springproject2.repository.CommentRepository;
import com.project.springproject2.repository.PostRepository;
import com.project.springproject2.security.provider.JwtTokenProvider;
import com.project.springproject2.service.CommentService;
import com.project.springproject2.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentRepository commentRepository;
    private final CommentService commentService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/api/auth/comment")
    public ResponseEntity<?> createComment(@RequestBody CommentRequestDto requestDto,@RequestHeader(HttpHeaders.AUTHORIZATION) String token) throws Exception {
        String nickname = jwtTokenProvider.getUserPk(token);
        Comment comment = new Comment(requestDto,nickname);
        commentRepository.save(comment);
        Message message = new Message();
        message.setData(comment);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/api/comment/{id}")
    public ResponseEntity<?> getComments(@PathVariable Long id) {
        Message message = new Message();
        List<?> comment=commentRepository.findCommentById(id);
        message.setData(comment);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping("/api/auth/comment/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CommentRequestDto requestDto,@RequestHeader(HttpHeaders.AUTHORIZATION) String token) throws Exception {
        String nickname = jwtTokenProvider.getUserPk(token);
        if (!commentService.checkAuthor(id, nickname)){
            Message message = new Message();
            message.setData("작성자만 삭제할 수 있습니다.");
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }

        commentService.update(id, requestDto, nickname);
        Message message = new Message();
        List <?> comment=commentRepository.findCommentById(id);
        message.setData(comment);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @DeleteMapping("/api/auth/comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id ,@RequestHeader(HttpHeaders.AUTHORIZATION) String token) throws Exception {
        String nickname = jwtTokenProvider.getUserPk(token);
        if (!commentService.checkAuthor(id, nickname)){
            Message message = new Message();
            message.setData("작성자만 삭제할 수 있습니다.");
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
        commentRepository.deleteById(id);
        Message message = new Message();
        message.setData("success");
        return new ResponseEntity<>(message, HttpStatus.CREATED);

    }


}