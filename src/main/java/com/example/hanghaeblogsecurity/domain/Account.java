package com.example.hanghaeblogsecurity.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Account{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "account_id")
    private Long accountId;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public Account(String nickname, String password, UserRoleEnum role) {
        this.nickname = nickname;
        this.password = password;
        this.role = role;
    }
}
