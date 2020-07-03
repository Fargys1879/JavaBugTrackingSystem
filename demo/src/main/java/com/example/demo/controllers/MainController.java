package com.example.demo.controllers;

import com.example.demo.models.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("title", "Main page");
        return "home";
    }

    @GetMapping("/projects")
    public String about( Model model) {
        model.addAttribute("title", "Projects");
        return "projects";
    }

}
