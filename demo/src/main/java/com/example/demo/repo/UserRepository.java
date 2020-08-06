package com.example.demo.repo;

import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс  UserRepository наследуемый от JPA Repository.
 * Предназначен для того что бы реализовать связь Класса-сущности User
 * с Базой Данных
 * findByUsername(String username) метод реализации поиска User по String username
 */

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
