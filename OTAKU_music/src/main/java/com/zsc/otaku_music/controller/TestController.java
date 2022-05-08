package com.zsc.otaku_music.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/")
    public String toPlayer() {
        return "player";
    }

    @GetMapping("/userLogin")
    public String toLogin() {
        return "login/login";
    }
}
