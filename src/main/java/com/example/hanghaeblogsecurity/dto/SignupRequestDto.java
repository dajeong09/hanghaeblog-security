package com.example.hanghaeblogsecurity.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
public class SignupRequestDto {
    //@Size(min = 4, max = 12)
    @Pattern(regexp = "[a-zA-Z\\d]*${3,12}")
    private String nickname;
    //@Size(min = 4, max = 32)
    @Pattern(regexp = "[a-z\\d]*${3,32}")
    private String password;
    private String passwordConfirm;
    private boolean admin = false;
    private String adminToken = "";
}

