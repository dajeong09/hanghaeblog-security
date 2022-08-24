package com.example.hanghaeblogsecurity;

import com.example.hanghaeblogsecurity.service.MyUserDetails;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
}
