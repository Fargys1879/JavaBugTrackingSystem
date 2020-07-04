package com.example.demo.models;

import javax.persistence.*;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title, description, type, project;
    private int priority;
    //private boolean progress;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    //public boolean isProgress() {
    //    return progress;
    //}

    //public void setProgress(boolean progress) {
    //    this.progress = progress;
    //}

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Task() {
    }

    public Task( String title, String description, String type, String project, int priority,User author) {

        this.title = title;
        this.description = description;
        this.type = type;
        this.priority = priority;
        this.project = project;
        this.author = author;
    }

    public String getAuthorName() {
        return  author != null ? author.getUsername() : "<none>";
    }


}
