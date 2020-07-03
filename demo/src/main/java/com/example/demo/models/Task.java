package com.example.demo.models;

import javax.persistence.*;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title, full_text, type;
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

    public String getFull_text() {
        return full_text;
    }

    public void setFull_text(String full_text) {
        this.full_text = full_text;
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

    public Task( String title, String full_text, String type, int priority,User author) {

        this.title = title;
        this.full_text = full_text;
        this.type = type;
        this.priority = priority;
        //this.progress = progress;
        this.author = author;
    }

    public String getAuthorName() {
        return  author != null ? author.getUsername() : "<none>";
    }


}
