package com.example.hanghaeblogsecurity.controller;

import com.example.hanghaeblogsecurity.dto.SigninRequestDto;
import com.example.hanghaeblogsecurity.dto.SigninResponseDto;
import com.example.hanghaeblogsecurity.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<SigninResponseDto> signinAccount(@RequestBody @Valid SigninRequestDto signinRequestDto, HttpServletResponse response) {
        return ResponseEntity.ok(authService.signinAccount(signinRequestDto, response));
    }
}
