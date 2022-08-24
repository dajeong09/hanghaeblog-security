//package com.example.hanghaeblogsecurity.jwt;
//
//import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.DefaultSecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
//    JwtUtils jwtUtils;
//
//    public JwtSecurityConfig(JwtUtils jwtUtils) {
//        this.jwtUtils = jwtUtils;
//    }
//
//    @Override
//    public void configure(HttpSecurity httpSecurity) {
//        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(jwtUtils);
//        httpSecurity.addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//    }
//}
