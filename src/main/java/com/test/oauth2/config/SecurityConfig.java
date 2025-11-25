package com.test.oauth2.config;

import com.test.oauth2.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomOAuth2LoginSuccessHandler successHandler;

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

        //OAuth2
        http.oauth2Login(auth->auth
                .loginPage("/login")
                .userInfoEndpoint(config -> config.userService(customOAuth2UserService))
        );
        
        //회원 정보 확인 -> DB 추가 입력
        http.oauth2Login(auth -> auth
                .successHandler(successHandler)
        );
        
        return http.build();
    }
}
