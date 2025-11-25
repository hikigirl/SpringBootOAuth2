package com.test.oauth2.config;

import com.test.oauth2.dto.CustomOAuth2User;
import com.test.oauth2.entity.User;
import com.test.oauth2.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomOAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {


        //로그인 성공시 호출된다.

        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String userName = customOAuth2User.getUsername();
        Optional<User> user = userRepository.findByUsername(userName);
        if(user.isPresent()) {
            response.sendRedirect("/"); //이미 등록한 사람
        } else {
            //처음 온 사람 > 추가 정보 입력 페이지
            response.sendRedirect("/join");
        }

    }
}
