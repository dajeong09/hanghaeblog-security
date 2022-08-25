package com.example.hanghaeblogsecurity.controller;

import com.example.hanghaeblogsecurity.domain.Account;
import com.example.hanghaeblogsecurity.dto.SigninRequestDto;
import com.example.hanghaeblogsecurity.dto.SigninResponseDto;
import com.example.hanghaeblogsecurity.jwt.JwtUtils;
import com.example.hanghaeblogsecurity.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<SigninResponseDto> signinAccount(@RequestBody @Valid SigninRequestDto signinRequestDto, HttpServletResponse response) {
        SigninResponseDto signinResponseDto = authService.signinAccount(signinRequestDto);
        setTokenHeader(response, signinResponseDto);
        return ResponseEntity.ok(signinResponseDto);
    }
    private void setTokenHeader(HttpServletResponse response, SigninResponseDto signinResponseDto) {
        response.addHeader("Access-Token", signinResponseDto.getAccessToken());
        response.addHeader("Refresh-Token",signinResponseDto.getRefreshToken());
    }

    @GetMapping("/account")
    //@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<Account> getMyUserInfo() {
        return ResponseEntity.ok(authService.getMyUserWithAuthorities());
    }

    @PostMapping("/signout")
    public void logout(HttpServletRequest request) {
        if (!jwtUtils.isValidateToken((request.getHeader("Refresh-Token")))) {
            throw new Error("Token이 유효하지 않습니다.");
        }
        Account account = jwtUtils.getAccountFromAuthentication();
        if (null == account) {
            throw new Error("사용자를 찾을 수 없습니다.");
        }
        jwtUtils.deleteRefreshToken(account);
    }
}
