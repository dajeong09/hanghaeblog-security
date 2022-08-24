package com.example.hanghaeblogsecurity.service;

import com.example.hanghaeblogsecurity.dto.SigninRequestDto;
import com.example.hanghaeblogsecurity.dto.SigninResponseDto;
import com.example.hanghaeblogsecurity.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Transactional
    public SigninResponseDto signinAccount(SigninRequestDto signinRequestDto, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(signinRequestDto.getNickname(), signinRequestDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SigninResponseDto signinResponseDto = jwtUtils.generateToken(authentication);
        /* 추가 */
        setTokenHeader(response, signinResponseDto);
        return signinResponseDto;
    }
    private void setTokenHeader(HttpServletResponse response, SigninResponseDto signinResponseDto) {
        response.addHeader("Access-Token", signinResponseDto.getAccessToken());
        response.addHeader("Refresh-Token",signinResponseDto.getRefreshToken());
    }
}
