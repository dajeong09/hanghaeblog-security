package com.example.hanghaeblogsecurity.service;

import com.example.hanghaeblogsecurity.domain.Account;
import com.example.hanghaeblogsecurity.domain.UserRoleEnum;
import com.example.hanghaeblogsecurity.dto.SignupRequestDto;
import com.example.hanghaeblogsecurity.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AccountServiceImpl implements AccountService, UserDetailsService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        Account account = accountRepository.findByNickname(nickname);
        if(account == null) {
            log.error("회원 정보가 존재하지 않습니다.");
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        } else {
            log.info("찾으려는 유저 정보: {}", nickname);
        }
        return new MyUserDetails(account);
    }

    @Override
    public Account signupAccount(SignupRequestDto signupRequestDto) {
        // 닉네임 중복확인
        String nickname = signupRequestDto.getNickname();
        Account found = accountRepository.findByNickname(nickname);
        if (found != null) {
            throw new IllegalArgumentException("중복된 닉네임입니다.");
        }
        // 패스워드&패스워드확인 일치여부
        if (!signupRequestDto.getPassword().equals(signupRequestDto.getPasswordConfirm())) {
            throw new Error("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }
        // 패스워드 암호화
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        // ROLE 확인
        UserRoleEnum role = UserRoleEnum.ROLE_USER;
        //관리자 권한 계정 생성
//        if (signupRequestDto.isAdmin()) {
//            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
//                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
//            }
//            role = UserRoleEnum.ROLE_ADMIN;
//        }
        // DB 저장
        Account account = new Account(signupRequestDto.getNickname(), password, role);
        log.info("{} 회원 정보를 DB에 저장", account.getNickname());
        return accountRepository.save(account);
    }


}
