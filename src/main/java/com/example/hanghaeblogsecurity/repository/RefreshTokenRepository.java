package com.example.hanghaeblogsecurity.repository;

import com.example.hanghaeblogsecurity.domain.Account;
import com.example.hanghaeblogsecurity.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    //Optional<RefreshToken> findByKey(String key);
    Optional<RefreshToken> findByAccount(Account account);
}