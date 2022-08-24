package com.example.hanghaeblogsecurity.repository;

import com.example.hanghaeblogsecurity.domain.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findById(Long accountId);
    Account findByNickname(String nickname);
    @EntityGraph(attributePaths = "authorities")
    Optional<Account> findOneWithAuthoritiesByNickname(String nickname);
}
