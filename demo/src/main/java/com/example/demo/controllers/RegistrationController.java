package com.example.demo.controllers;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

/**
 * Класс RegistrationController назначен для логики отображений страниц
 * для регистрации/авторизации новых пользователей
 * @Controller привязывает класс к Spring Boot stereotype
 * @author Evgeny Shabalin
 */

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    /**
     * Метод для регистрации нового пользователя
     * @param user обьект класса User который будет зарегистрирован
     * @param model Map для отображения уже существующего User
     * @return
     */
    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model ) {
        User UserFromDB = userRepo.findByUsername(user.getUsername());
        if (UserFromDB != null) {
            model.put("message", "User exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/login";

    }
}
