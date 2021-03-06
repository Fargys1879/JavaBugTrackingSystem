package com.example.demo.config;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


/**
 * Класс WebSecurityConfig назначен для защиты доступа к страницам приложения
 * @EnableWebSecurity, чтобы включить поддержку веб-безопасности Spring Security
 * и обеспечить интеграцию Spring MVC. Он также расширяет WebSecurityConfigurerAdapter
 * и переопределяет несколько его методов, чтобы установить некоторые особенности конфигурации веб-безопасности.
 * @author Evgeny Shabalin
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "/registration", "/h2-console/*").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll();


        //ВНИМАНИЕ! Это не конфигурация безопасности Spring, которую вы хотели бы использовать
        // для рабочего веб-сайта. Эти настройки предназначены только для поддержки разработки
        // веб-приложения Spring Boot и обеспечения доступа к консоли базы данных H2
        /////////////////////////////////////////////////////////////////////////////////////
        http.csrf().disable();
        http.headers().frameOptions().disable();
        ////////////////////////////////////////////////////////////////////////////////////

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());

    }
}
