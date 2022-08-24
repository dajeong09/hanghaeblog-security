package com.example.hanghaeblogsecurity.jwt;

import com.example.hanghaeblogsecurity.domain.Account;
import com.example.hanghaeblogsecurity.domain.RefreshToken;
import com.example.hanghaeblogsecurity.domain.UserRoleEnum;
import com.example.hanghaeblogsecurity.dto.SigninResponseDto;
import com.example.hanghaeblogsecurity.repository.RefreshTokenRepository;
import com.example.hanghaeblogsecurity.service.MyUserDetails;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtUtils {

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.secret-key}")
    private String secretKey;

    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 10;
    private static final long REFRESH_TOKEN_EXPRIRE_TIME = 1000 * 60 * 30;

    private static final String AUTHORITIES_KEY = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    @PostConstruct
    private void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public SigninResponseDto generateToken(Authentication authentication) {

        long now = (new Date().getTime());

        //Access Token 생성
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, UserRoleEnum.ROLE_USER.toString())
                .setExpiration(new Date(now + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        //Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setSubject(authentication.getName())
                .setExpiration(new Date(now + REFRESH_TOKEN_EXPRIRE_TIME))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        RefreshToken refreshTokenObject = RefreshToken.builder()
                .key(authentication.getName())
                .account(((MyUserDetails) authentication.getPrincipal()).getAccount())
                .value(refreshToken)
                .build();       //key or account 둘 중 하나만 사용

        refreshTokenRepository.save(refreshTokenObject);

        return SigninResponseDto.builder()
                .grantType(BEARER_PREFIX)
                .accessToken(accessToken)
                .accessTokenExpiresIn(new Date(now + ACCESS_TOKEN_EXPIRE_TIME).getTime())
                .refreshToken(refreshToken)
                .build();
    }


    public Authentication getAuthentication(String token) {
        Claims claims = Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        UserDetails principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean isValidateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (MalformedJwtException e) {
            log.info("잘못된 JWT 토큰입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

    @Transactional(readOnly = true)
    public RefreshToken isPresentRefreshToken(Account account) {
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByAccount(account);
        return optionalRefreshToken.orElse(null);
    }

    @Transactional
    public void deleteRefreshToken(Account account) {
        RefreshToken refreshToken = isPresentRefreshToken(account);
        if (null == refreshToken) {
            throw new Error("존재하지 않는 Token 입니다.");
        }
        refreshTokenRepository.delete(refreshToken);
    }

}