package com.example.demo.models;

import javax.persistence.*;
import java.util.Collection;

/**
 * Класс-сущность Project назначен для проектов и их параметров.
 * @Entity устанавливает связь с JPA Repository
 * @Table(name = "project") определяет имя таблицы в БД.
 * @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL) устанавливает связь
 * между сущностями с использованием "ленивой" загрузки связанных обьектов из БД, а так же устанавливает
 * каскадную связь между сущностями(если данный обьект будет удален и т.д. связанный с ним обьект тоже потерпит изменение)
 * @author Evgeny Shabalin
 */

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String project_name;
    private String description;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Task> tasks;

    public Project() {
    }

    public Project(String project_name) {
        this.project_name = project_name;
    }

    public Project(String project_name, String description) {
        this.project_name = project_name;
        this.description = description;
    }

    public Collection<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Collection<Task> tasks) {
        this.tasks = tasks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    @Override
    public String toString() {
        return project_name;
    }
}
