package com.example.hanghaeblogsecurity.service;

import com.example.hanghaeblogsecurity.domain.Account;
import com.example.hanghaeblogsecurity.dto.SignupRequestDto;

public interface AccountService {
    Account signupAccount(SignupRequestDto signupRequestDto);

}
