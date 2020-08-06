package com.example.demo.models;

import org.springframework.security.core.GrantedAuthority;

/**
 * Enum-сущность назначен для состояния роли пользователя.
 * @author Evgeny Shabalin
 */

public enum  Role implements GrantedAuthority {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
