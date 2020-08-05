package com.example.demo.controllers;

import com.example.demo.models.Role;
import com.example.demo.models.Task;
import com.example.demo.models.User;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/user")
    public String userList(Model model) {
        Iterable<User> users = userRepo.findAll();

        model.addAttribute("users", users);
        return "userList";
    }

    @GetMapping("/user/{id}/edit")
    public String userEditForm( @PathVariable(value = "id") Long id,  Model model) {
        if(!userRepo.existsById(id)) {
            return "redirect:/user";
        }
        Optional<User> user = userRepo.findById(id);
        ArrayList<User> res = new ArrayList<>();
        user.ifPresent(res::add);
        model.addAttribute("user", res);
        model.addAttribute("roles", Role.values());
        return "userEdit";

    }

    @PostMapping("/user/{id}/edit")
    public String userSave(@RequestParam String userName,
                           @RequestParam String userPassword,
                           @PathVariable(value = "id") User user,Model model) {
        user.setUsername(userName);
        user.setPassword(userPassword);
        userRepo.save(user);
        return "redirect:/user";
    }
}
