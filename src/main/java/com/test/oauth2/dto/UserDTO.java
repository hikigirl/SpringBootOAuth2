package com.test.oauth2.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long seq;
    private String username;
    private String name;
    private String email;
    private String role;
    private String provider; //Google, Naver, kakao, 등등.. oauth2 제공업체
    private String providerId;

    
}
