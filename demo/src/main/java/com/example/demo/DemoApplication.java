package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Главный Класс DemoAplication для запуска приложения Java Bug Tracking System.
 * @SpringBootApplication подключает Spring Boot framework.
 * @author Evgeny Shabalin
 *
 */

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
