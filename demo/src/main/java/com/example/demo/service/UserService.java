package com.example.demo.service;

import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Класс  UserService наследуемый от UserDetailsService.
 * Интерфейс UserDetailsService  который загружает пользовательские данные.
 * @Override
 * public UserDetails loadUserByUsername(String username) переопределение данного метода
 * для поска User по username в репозитории.
 * @author Evgeny Shabalin
 */

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }
}
