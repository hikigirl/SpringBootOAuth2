package com.test.oauth2.controller;

import com.test.oauth2.dto.CustomOAuth2User;
import com.test.oauth2.entity.User;
import com.test.oauth2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserRepository repository;

    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/member")
    public String member() {
        return "member";
    }
    @GetMapping("/join")
    public String join(@AuthenticationPrincipal CustomOAuth2User oAuth2User, Model model) {
        if(oAuth2User != null) {
            model.addAttribute("email", oAuth2User.getUserDTO().getEmail());
            model.addAttribute("name", oAuth2User.getUserDTO().getName());
        }
        return "join";
    }
    @PostMapping("/joinok")
    public String joinok(@AuthenticationPrincipal CustomOAuth2User oAuth2User, @RequestParam("email") String email, @RequestParam("name") String name) {
        User user = User.builder()
                .name(name)
                .email(email)
                .username(oAuth2User.getUsername())
                .role("ROLE_MEMBER")
                .provider(oAuth2User.getProvider())
                .providerid(oAuth2User.getProviderId())
                .build();


        System.out.println(user);
        //repository.save(user);

        return "redirect:/";
    }
}
