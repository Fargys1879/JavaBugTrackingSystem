package com.example.demo.repo;

import com.example.demo.models.Task;
import org.springframework.data.repository.CrudRepository;

/**
 * Интерфейс  TaskRepository наследуемый от JPA Repository.
 * Предназначен для того что бы реализовать связь Класса-сущности Task
 * с Базой Данных
 * @author Evgeny Shabalin
 */

public interface TaskRepository extends CrudRepository<Task, Long> {
}
