package com.example.hanghaeblogsecurity.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SigninRequestDto {
    @NotBlank
    private String nickname;
    @NotBlank
    private String password;
}