package com.example.demo.repo;

import com.example.demo.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс  ProjectRepository наследуемый от JPA Repository.
 * Предназначен для того что бы реализовать связь Класса-сущности Project
 * с Базой Данных
 *
 */

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
