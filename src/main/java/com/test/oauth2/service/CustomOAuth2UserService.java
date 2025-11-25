package com.test.oauth2.service;

import com.test.oauth2.dto.CustomOAuth2User;
import com.test.oauth2.dto.GoogleResponse;
import com.test.oauth2.dto.UserDTO;
import com.test.oauth2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    //private final UserRepository userRepository;

    //DB로부터 받아오는 정보 -> 인증 객체..
    //리소스 서버(구글)로부터 받아오는 개인 정보 -> 인증 객체
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("구글로부터 받아온 개인 정보 >>>> " + oAuth2User);
        /*
            구글로부터 받아온 개인 정보 >>>> Name: [101497572519366370951], Granted Authorities: [[OAUTH2_USER, SCOPE_https://www.googleapis.com/auth/userinfo.email, SCOPE_https://www.googleapis.com/auth/userinfo.profile, SCOPE_openid]], User Attributes: [{sub=101497572519366370951, name=구, given_name=구, picture=https://lh3.googleusercontent.com/a/ACg8ocLKBEn8SLEppbRzc_5_ta3quPxOObJzzAEcLpl1YjHMYVDx8oNSXg=s96-c, email=sunmin323233@gmail.com, email_verified=true}]
        */

        //구글 정보를 파싱
        GoogleResponse oauth2Response = new GoogleResponse(oAuth2User.getAttributes());

        // 타 사이트와의 아이디 중복을 피하기 위해 username을 미리 처리
        String username = oauth2Response.getProvider() + " " + oauth2Response.getProviderId();
        System.out.println("username >>>>> " + username);

        UserDTO userDTO = UserDTO.builder()
                .username(username)
                .name(oauth2Response.getName())
                .role("ROLE_MEMBER")
                .email(oauth2Response.getEmail())
                .provider(oauth2Response.getProvider())
                .providerId(oauth2Response.getProviderId())
                .build();

        return new CustomOAuth2User(userDTO);
    }
}
