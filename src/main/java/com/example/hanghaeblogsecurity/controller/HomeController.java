package com.example.hanghaeblogsecurity.controller;

import com.example.hanghaeblogsecurity.domain.Account;
import com.example.hanghaeblogsecurity.dto.SignupRequestDto;
import com.example.hanghaeblogsecurity.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class HomeController {
    private final AccountService accountService;

    @PostMapping("/signup")
    public ResponseEntity<Account> signupAccount(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        return ResponseEntity.ok(accountService.signupAccount(signupRequestDto));   //Account(domain) 으로 리턴
    }


}
