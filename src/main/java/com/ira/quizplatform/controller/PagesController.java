package com.ira.quizplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PagesController {

    @RequestMapping("/home")
    public String home() {
        return "home";
    }
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    @RequestMapping("/")
    public String index() {
        return "hello";
    }
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}
