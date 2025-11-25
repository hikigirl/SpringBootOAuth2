package com.test.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //CSRF 비활성
        http.csrf(auth -> auth.disable());
        //폼 로그인 비활성
        http.formLogin(auth -> auth.disable());
        //기본 인증 비활성
        http.httpBasic(auth -> auth.disable());

        //허가 URL
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login/**", "/oauth2/**").permitAll()
                .anyRequest().authenticated()
        );

        return http.build();
    }
}
