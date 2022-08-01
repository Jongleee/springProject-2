package com.project.springproject2.controller;

import com.project.springproject2.model.User;
import com.project.springproject2.repository.UserRepository;
import com.project.springproject2.security.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    // 회원 가입 요청 처리
    @PostMapping("/member/signup")
    public Long registerUser(@RequestBody Map<String, String> user) {
        return userRepository.save(User.builder()
                .nickname(user.get("nickname"))
                .password(passwordEncoder.encode(user.get("password")))
                .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
                .build()).getId();
    }

    // 회원 관련 정보 받기
    @PostMapping("/member/login")
    public String login(@RequestBody Map<String, String> user) {
        User member = userRepository.findByNickname(user.get("nickname"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 아이디 입니다."));
        if (!passwordEncoder.matches(user.get("password"), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
    }


}