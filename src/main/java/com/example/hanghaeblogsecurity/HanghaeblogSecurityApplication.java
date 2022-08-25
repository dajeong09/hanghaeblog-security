package com.example.hanghaeblogsecurity;

import com.example.hanghaeblogsecurity.domain.Account;
import com.example.hanghaeblogsecurity.dto.SignupRequestDto;
import com.example.hanghaeblogsecurity.service.AccountService;
import com.example.hanghaeblogsecurity.service.MyUserDetails;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

@EnableJpaAuditing
@SpringBootApplication
public class HanghaeblogSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(HanghaeblogSecurityApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MyUserDetails myUserDetails() {
        return new MyUserDetails();
    }

    @Bean
    public String string() {
        return new String();
    }

    @Bean
    CommandLineRunner run(AccountService accountService) {

        SignupRequestDto signupRequestDto = new SignupRequestDto();
        signupRequestDto.setNickname("nickname");
        signupRequestDto.setPassword("password");
        signupRequestDto.setPasswordConfirm("password");

        return args -> {
            accountService.signupAccount(signupRequestDto);

        };
    }
}
