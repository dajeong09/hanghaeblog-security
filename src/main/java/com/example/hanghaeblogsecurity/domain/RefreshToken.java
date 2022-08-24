package com.example.hanghaeblogsecurity.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class RefreshToken extends Timestamped{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "token_id")
    private Long tokenId;

    @Column(nullable = false, name = "token_key")
    private String key;

    @Column(nullable = false, name = "token_value")
    private String value;

    @OneToOne(fetch = FetchType.LAZY)
    private Account account;

    @Builder
    public RefreshToken(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public RefreshToken updateValue(String token) {
        this.value = token;
        return this;
    }

}
