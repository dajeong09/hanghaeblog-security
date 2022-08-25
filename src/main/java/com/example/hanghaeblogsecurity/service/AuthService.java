package com.example.hanghaeblogsecurity.service;

import com.example.hanghaeblogsecurity.domain.Account;
import com.example.hanghaeblogsecurity.dto.SigninRequestDto;
import com.example.hanghaeblogsecurity.dto.SigninResponseDto;
import com.example.hanghaeblogsecurity.jwt.JwtUtils;
import com.example.hanghaeblogsecurity.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final AccountRepository accountRepository;

    @Transactional
    public SigninResponseDto signinAccount(SigninRequestDto signinRequestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(signinRequestDto.getNickname(), signinRequestDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SigninResponseDto signinResponseDto = jwtUtils.generateToken(authentication);
        return signinResponseDto;
    }


    @Transactional
    public Account getMyUserWithAuthorities() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            log.debug("Security Context에 인증 정보가 없습니다.");
            return null;
        }
        String username = null;
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            username = springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            username = (String) authentication.getPrincipal();
        }
        return accountRepository.findByNickname(username);
    }
}
