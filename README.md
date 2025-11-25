# OAuth2
### 파일생성
- com.test.oauth2
  - controller
    - `MainController.java`
  - config
    - `SecurityConfig.java`
    - `CustomOAuth2LoginSuccessHandler.java`
  - dto
    - `UserDTO.java`
    - `CustomOAuth2User.java`
    - `GoogleResponse.java`
  - repository
    - `UserRepository.java`(I)
  - entity
    - `User.java`
  - service
    - `CustomOAuth2UserService.java` : CustomUserDetailService와 비슷한 역할...
- templates
  - index.html
  - login.html
  - member.html
- templates/inc
    - header.html

### OAuth2 작업

1. SecurityConfig -> OAuth2 처리 추가
2. DB 파일들 작업 
3. 로그인 페이지 구현(front)
   1. https://developers.google.com/identity/branding-guidelines
4. DB에 저장하기
   1. 처음: 시작 - 로그인 - 구글아이콘 클릭 - 구글 인증 - 로그인 완료 - (개입)
   2. 개입할때 확인할 내용
      1. 첫방문
         1. DB 조회
         2. 개인정보 추가 입력
         3. DB 추가
      2. 재방문
         1. DB 조회
5. 