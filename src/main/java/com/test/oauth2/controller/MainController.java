package com.test.oauth2.controller;

import com.test.oauth2.dto.CustomOAuth2User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
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
}
