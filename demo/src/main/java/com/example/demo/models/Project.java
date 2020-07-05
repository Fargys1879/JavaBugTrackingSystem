package com.example.demo.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String project_name;

    //@OneToOne(mappedBy = "proj", cascade = CascadeType.ALL)
    //private Task task;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private List<Task> tasks;


    public Project() {
    }

    public Project(String project_name) {
        this.project_name = project_name;

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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "\nProject{" +
                "id=" + id +
                ", project_name='" + project_name + '\'' +
                ", task=" + tasks +
                '}';
    }
}
